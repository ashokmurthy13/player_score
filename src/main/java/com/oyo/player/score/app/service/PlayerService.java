package com.oyo.player.score.app.service;

import com.oyo.player.score.app.exception.ScoreBaseException;
import com.oyo.player.score.app.model.*;
import com.oyo.player.score.app.repository.ScoreRepository;
import com.oyo.player.score.app.util.ResponseBuilder;
import com.oyo.player.score.app.validation.ValidateRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerService.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final @NonNull ValidateRequest validateRequest;
    private final @NonNull ScoreRepository scoreRepository;

    public RestResponse<ScoreResponse> save(AddScoreRequest addScoreRequest) {
        validateRequest.validateAddScoreRequest(addScoreRequest);
        try {
            String player = addScoreRequest.getPlayer().toLowerCase();
            LocalDateTime time = convertTime(addScoreRequest.getTime());
            ScoreInfo scoreInfo = new ScoreInfo(player, addScoreRequest.getScore(), time);
            ScoreInfo score = scoreRepository.save(scoreInfo);
            return new RestResponse<>(ResponseBuilder.build(score), HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Exception while adding a score: {}", e.getMessage());
            throw new ScoreBaseException("Exception in creating score");
        }
    }

    private static LocalDateTime convertTime(String time) {
        return LocalDateTime.parse(time, FORMATTER);
    }

    public RestResponse<ScoreResponse> findScoreById(Long scoreId) {
        Optional<ScoreInfo> result = scoreRepository.findById(scoreId);
        if (result.isPresent()) {
            ScoreInfo scoreInfo = result.get();
            return new RestResponse<>(ResponseBuilder.build(scoreInfo), HttpStatus.OK);
        } else {
            throw new ScoreBaseException("Score not found for the id " + scoreId);
        }
    }

    public RestResponse<String> deleteScoreById(Long scoreId) {
        try {
            scoreRepository.deleteById(scoreId);
        } catch (Exception e) {
            LOG.error("error while deleting score: {}", e.getMessage());
            throw new ScoreBaseException("score not found for the id " + scoreId);
        }
        return new RestResponse<>("deleted score with id " + scoreId, HttpStatus.OK);
    }

    public RestResponse<PageResult> getAllScores(List<String> players, String beforeDate, String afterDate, Integer size, Integer page, Pageable pageable) {
        validateRequest.validateDateFormat(beforeDate);
        validateRequest.validateDateFormat(afterDate);
        List<ScoreInfo> scoreInfoList;
        if (players != null && !players.isEmpty() && StringUtils.hasText(beforeDate) && StringUtils.hasText(afterDate)) {
            validateRequest.validateDate(beforeDate, afterDate);
            scoreInfoList = scoreRepository.findScoreByAllFields(players, beforeDate, afterDate);
        } else if (players != null && !players.isEmpty() && StringUtils.hasText(beforeDate)) {
            scoreInfoList = scoreRepository.findScoresByBeforeDate(players, beforeDate);
        } else if (players != null && !players.isEmpty() && StringUtils.hasText(afterDate)) {
            scoreInfoList = scoreRepository.findScoresByAfterDate(players, afterDate);
        } else if (StringUtils.hasText(beforeDate) && StringUtils.hasText(afterDate)) {
            validateRequest.validateDate(beforeDate, afterDate);
            scoreInfoList = scoreRepository.findScoreByDate(beforeDate, afterDate);
        } else if (StringUtils.hasText(beforeDate)) {
            scoreInfoList = scoreRepository.findScoreByBeforeDate(beforeDate);
        } else if (StringUtils.hasText(afterDate)) {
            scoreInfoList = scoreRepository.findScoreByAfterDate(afterDate);
        } else if (players != null && !players.isEmpty() && size != null && page != null) {
            scoreInfoList = scoreRepository.findByNames(players, pageable);
        } else {
            scoreInfoList = scoreRepository.getAllScores(pageable);
        }
        List<ScoreResponse> response = new ArrayList<>();
        scoreInfoList.forEach(score -> response.add(ResponseBuilder.build(score)));
        return new RestResponse<>(new PageResult(response, pageable.getPageNumber(), size != null ? size : pageable.getPageSize(), response.size()), HttpStatus.OK);
    }

    public RestResponse<PlayerHistory> getPlayersHistory(String player) {
        player = player.toLowerCase();
        ScoreInfo topScore = scoreRepository.getTopScore(player);
        ScoreInfo lowScore = scoreRepository.getLowScore(player);
        Double avgScore = scoreRepository.getAvgScore(player);
        List<ScoreInfo> scoreList = scoreRepository.getPlayerHistory(player);
        PlayerHistory playerHistory = new PlayerHistory();
        Score top = new Score(topScore.getScore(), topScore.getTime());
        Score low = new Score(lowScore.getScore(), lowScore.getTime());
        playerHistory.setTopScore(top);
        playerHistory.setLowScore(low);
        playerHistory.setAvgScore(avgScore);
        List<Score> scores = new ArrayList<>();
        scoreList.forEach(scoreData -> {
            Score score = new Score(scoreData.getScore(), scoreData.getTime());
            scores.add(score);
        });
        playerHistory.setScores(scores);
        return new RestResponse<>(playerHistory, HttpStatus.OK);
    }
}

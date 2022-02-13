package com.oyo.player.score.app.service;

import com.oyo.player.score.app.exception.ScoreBaseException;
import com.oyo.player.score.app.model.AddScoreRequest;
import com.oyo.player.score.app.model.RestResponse;
import com.oyo.player.score.app.model.ScoreInfo;
import com.oyo.player.score.app.model.ScoreResponse;
import com.oyo.player.score.app.repository.ScoreRepository;
import com.oyo.player.score.app.util.ResponseBuilder;
import com.oyo.player.score.app.validation.ValidateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerService.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private ValidateRequest validateRequest;

    @Autowired
    private ScoreRepository scoreRepository;

    public RestResponse<ScoreResponse> save(AddScoreRequest addScoreRequest) {
        validateRequest.checkScore(addScoreRequest.getScore());
        try {
            String player = addScoreRequest.getPlayer().toLowerCase();
            ScoreInfo score = scoreRepository.save(new ScoreInfo(player, addScoreRequest.getScore(), convertTime(addScoreRequest.getTime())));
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
}

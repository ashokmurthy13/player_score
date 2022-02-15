package com.oyo.player.score.app.service;

import com.oyo.player.score.app.exception.ScoreBaseException;
import com.oyo.player.score.app.model.*;
import com.oyo.player.score.app.repository.ScoreRepository;
import com.oyo.player.score.app.validation.ValidateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private static final Long scoreId = 1L;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private ValidateRequest validateRequest;

    @Test
    public void testCreateScoreException() {
        AddScoreRequest request = new AddScoreRequest("oyo", 99, "2020-02-14 10:30:00");
        PlayerService playerService = new PlayerService(validateRequest, scoreRepository);
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> playerService.save(request));
        Assertions.assertEquals("Exception in creating score", exception.getMessage());
    }

    @Test
    public void testFindScoreById() {
        Optional<ScoreInfo> scoreInfo = Optional.of(new ScoreInfo("test", 98, LocalDateTime.now()));
        when(scoreRepository.findById(scoreId)).thenReturn(scoreInfo);
        PlayerService playerService = new PlayerService(validateRequest, scoreRepository);
        RestResponse<ScoreResponse> response = playerService.findScoreById(scoreId);
        Assertions.assertEquals("test", response.getResult().getPlayer());
    }

    @Test()
    public void testFindScoreByIdException() {
        Optional<ScoreInfo> scoreInfo = Optional.empty();
        when(scoreRepository.findById(scoreId)).thenReturn(scoreInfo);
        PlayerService playerService = new PlayerService(validateRequest, scoreRepository);
        ScoreBaseException exception = Assertions.assertThrows(ScoreBaseException.class, () -> playerService.findScoreById(scoreId));
        Assertions.assertEquals("Score not found for the id 1", exception.getMessage());
    }

    @Test
    public void testDeleteScoreById() {
        PlayerService playerService = new PlayerService(validateRequest, scoreRepository);
        doNothing().when(scoreRepository).deleteById(scoreId);
        RestResponse<String> response = playerService.deleteScoreById(scoreId);
        Assertions.assertEquals("deleted score with id 1", response.getResult());
    }

    @Test
    public void testGetPlayersHistory() {
        String player = "oyo";
        ScoreInfo topScore = new ScoreInfo(90, LocalDateTime.now());
        ScoreInfo lowScore = new ScoreInfo(10, LocalDateTime.now());
        when(scoreRepository.getTopScore(player)).thenReturn(topScore);
        when(scoreRepository.getLowScore(player)).thenReturn(lowScore);
        when(scoreRepository.getAvgScore(player)).thenReturn(45.5);
        when(scoreRepository.getPlayerHistory(player)).thenReturn(new ArrayList<>());
        PlayerService playerService = new PlayerService(validateRequest, scoreRepository);
        RestResponse<PlayerHistory> response = playerService.getPlayersHistory(player);
        Assertions.assertEquals(90, response.getResult().getTopScore().getScore());
    }
}

package com.oyo.player.score.app.controller;

import com.oyo.player.score.app.model.*;
import com.oyo.player.score.app.service.PlayerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restapi/v1/player")
@RequiredArgsConstructor
public class PlayerController {

    private final @NonNull PlayerService playerService;

    @PostMapping("/score")
    public RestResponse<ScoreResponse> createScore(@RequestBody AddScoreRequest addScoreRequest) {
        return playerService.save(addScoreRequest);
    }

    @GetMapping("/score")
    public RestResponse<ScoreResponse> getScore(@RequestParam("scoreId") Long scoreId) {
        return playerService.findScoreById(scoreId);
    }

    @DeleteMapping("/score")
    public RestResponse<String> deleteScore(@RequestParam("scoreId") Long scoreId) {
        return playerService.deleteScoreById(scoreId);
    }

    @GetMapping("/scores")
    public RestResponse<PageResult> getScores(@RequestParam(value = "players", required = false) List<String> players,
                                              @RequestParam(value = "beforeDate", required = false) String beforeDate,
                                              @RequestParam(value = "afterDate", required = false) String afterDate,
                                              @RequestParam(value = "size", required = false) Integer size,
                                              @RequestParam(value = "page", required = false) Integer page,
                                              @PageableDefault Pageable pageable) {
        return playerService.getAllScores(players, beforeDate, afterDate, size, page, pageable);
    }

    @GetMapping("history")
    public RestResponse<PlayerHistory> getPlayersHistory(@RequestParam("player") String player) {
        return playerService.getPlayersHistory(player);
    }
}

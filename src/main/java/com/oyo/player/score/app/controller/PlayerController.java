package com.oyo.player.score.app.controller;

import com.oyo.player.score.app.model.AddScoreRequest;
import com.oyo.player.score.app.model.ScoreResponse;
import com.oyo.player.score.app.model.RestResponse;
import com.oyo.player.score.app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restapi/v1/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

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
}

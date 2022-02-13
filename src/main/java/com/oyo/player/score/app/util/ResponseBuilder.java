package com.oyo.player.score.app.util;

import com.oyo.player.score.app.model.ScoreInfo;
import com.oyo.player.score.app.model.ScoreResponse;

public class ResponseBuilder {

    public static ScoreResponse build(ScoreInfo scoreInfo) {
        ScoreResponse response = new ScoreResponse();
        response.setId(scoreInfo.getId());
        response.setPlayer(scoreInfo.getPlayer());
        response.setScore(scoreInfo.getScore());
        response.setTime(scoreInfo.getTime());
        return response;
    }
}

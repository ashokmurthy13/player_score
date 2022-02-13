package com.oyo.player.score.app.validation;

import com.oyo.player.score.app.exception.ScoreBaseException;
import org.springframework.stereotype.Service;

@Service
public class ValidateRequest {

    public void checkScore(Integer score) {
        if(score <= 0) {
            throw new ScoreBaseException("score should be greater than zero");
        }
    }
}

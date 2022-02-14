package com.oyo.player.score.app.model;

import java.time.LocalDateTime;

public class Score {

    private Integer score;
    private LocalDateTime time;

    public Score() {
    }

    public Score(Integer score, LocalDateTime time) {
        this.score = score;
        this.time = time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

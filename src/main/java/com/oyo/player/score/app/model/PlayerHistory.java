package com.oyo.player.score.app.model;

import java.util.List;

public class PlayerHistory {

    private Score topScore;
    private Score lowScore;
    private Double avgScore;
    private List<Score> scores;

    public Score getTopScore() {
        return topScore;
    }

    public void setTopScore(Score topScore) {
        this.topScore = topScore;
    }

    public Score getLowScore() {
        return lowScore;
    }

    public void setLowScore(Score lowScore) {
        this.lowScore = lowScore;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}

package com.oyo.player.score.app.model;

public class AddScoreRequest {

    private String player;
    private Integer score;
    private String time;

    public AddScoreRequest() {
    }

    public AddScoreRequest(String player, Integer score, String time) {
        this.player = player;
        this.score = score;
        this.time = time;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

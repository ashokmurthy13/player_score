package com.oyo.player.score.app.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "score")
public class ScoreInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "player")
    private String player;

    @Column(name = "score")
    private Integer score;

    @Column(name = "time")
    private LocalDateTime time;

    public ScoreInfo() {
    }

    public ScoreInfo(String player, Integer score, LocalDateTime time) {
        this.player = player;
        this.score = score;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

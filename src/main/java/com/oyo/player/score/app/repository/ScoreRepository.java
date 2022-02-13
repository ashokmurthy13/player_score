package com.oyo.player.score.app.repository;

import com.oyo.player.score.app.model.ScoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<ScoreInfo, Long> {

}

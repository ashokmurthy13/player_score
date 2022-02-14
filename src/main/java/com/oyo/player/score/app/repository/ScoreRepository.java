package com.oyo.player.score.app.repository;

import com.oyo.player.score.app.model.ScoreInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ScoreRepository extends JpaRepository<ScoreInfo, Long> {

    @Query(value = "select * from score where player IN (:players)", nativeQuery = true)
    List<ScoreInfo> findByNames(@Param("players") List<String> players, Pageable pageable);

    @Query(value = "select * from score ", nativeQuery = true)
    List<ScoreInfo> getAllScores(Pageable pageable);

    @Query(value = "select * from score where player IN (:players) and time < :beforeDate and time > :afterDate", nativeQuery = true)
    List<ScoreInfo> findScoreByAllFields(@Param("players") List<String> players, @Param("beforeDate") String beforeDate, @Param("afterDate") String afterDate);

    @Query(value = "select * from score where player IN (:players) and time < :beforeDate", nativeQuery = true)
    List<ScoreInfo> findScoresByBeforeDate(List<String> players, @Param("beforeDate") String beforeDate);

    @Query(value = "select * from score where player IN (:players) and time > :afterDate", nativeQuery = true)
    List<ScoreInfo> findScoresByAfterDate(List<String> players, @Param("afterDate") String afterDate);

    @Query(value = "select * from score where time < :beforeDate and time > :afterDate", nativeQuery = true)
    List<ScoreInfo> findScoreByDate(@Param("beforeDate") String beforeDate, @Param("afterDate") String afterDate);

    @Query(value = "select * from score where time < :beforeDate", nativeQuery = true)
    List<ScoreInfo> findScoreByBeforeDate(String beforeDate);

    @Query(value = "select * from score where time > :afterDate", nativeQuery = true)
    List<ScoreInfo> findScoreByAfterDate(String afterDate);
}

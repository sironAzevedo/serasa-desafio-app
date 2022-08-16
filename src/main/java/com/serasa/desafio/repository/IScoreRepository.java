package com.serasa.desafio.repository;

import com.serasa.desafio.model.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IScoreRepository extends JpaRepository<ScoreEntity, Long> {

    @Query("SELECT s FROM ScoreEntity s WHERE :score >= s.inicial AND :score <= s.faixaFinal")
    Optional<ScoreEntity> findScore(Long score);
}

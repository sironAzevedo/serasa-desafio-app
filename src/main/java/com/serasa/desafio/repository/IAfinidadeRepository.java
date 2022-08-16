package com.serasa.desafio.repository;

import com.serasa.desafio.model.entity.AfinidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAfinidadeRepository extends JpaRepository<AfinidadeEntity, Long> {

    Optional<AfinidadeEntity> findByRegiao(String regiao);


    @Query("SELECT a FROM AfinidadeEntity a " +
            "JOIN FETCH a.estados " +
            "WHERE a.regiao = :regiao")
    Optional<AfinidadeEntity> findByRegiaoEEstados(String regiao);
}

package com.serasa.desafio.repository;

import com.serasa.desafio.model.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPessoaRepository extends JpaRepository<PessoaEntity, Long> {
}

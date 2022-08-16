package com.serasa.desafio.service;

import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.dto.PessoaResponseDTO;

import java.util.List;

public interface IPessoaService {
    void inserir(PessoaRequestDTO dto);

    PessoaResponseDTO findById(Long codigo);

    List<PessoaResponseDTO> findAll();
}

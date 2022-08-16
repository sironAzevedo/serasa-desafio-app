package com.serasa.desafio.service;

import com.serasa.desafio.model.dto.ScoreRequestDTO;
import lombok.NonNull;

public interface IScoreService {
    void inserir(@NonNull ScoreRequestDTO dto);
}

package com.serasa.desafio.service.impl;

import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.model.entity.ScoreEntity;
import com.serasa.desafio.model.mapper.ScoreMapper;
import com.serasa.desafio.repository.IScoreRepository;
import com.serasa.desafio.service.IScoreService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScoreServiceImpl implements IScoreService {

    private final IScoreRepository scoreRepository;

    @Override
    public void inserir(@NonNull ScoreRequestDTO dto) {
        ScoreEntity from = ScoreMapper.INSTANCE.from(dto);
        scoreRepository.save(from);
    }
}

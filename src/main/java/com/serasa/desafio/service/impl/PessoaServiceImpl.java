package com.serasa.desafio.service.impl;

import com.serasa.desafio.handler.exception.BusinessException;
import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.dto.PessoaResponseDTO;
import com.serasa.desafio.model.entity.AfinidadeEstadoEntity;
import com.serasa.desafio.model.entity.PessoaEntity;
import com.serasa.desafio.model.entity.ScoreEntity;
import com.serasa.desafio.model.mapper.PessoaMapper;
import com.serasa.desafio.repository.IAfinidadeRepository;
import com.serasa.desafio.repository.IPessoaRepository;
import com.serasa.desafio.repository.IScoreRepository;
import com.serasa.desafio.service.IPessoaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements IPessoaService {
    private final IPessoaRepository pessoaRepository;
    private final IAfinidadeRepository afinidadeRepository;
    private final IScoreRepository scoreRepository;

    @Override
    public void inserir(@NonNull PessoaRequestDTO dto) {
        PessoaEntity pessoa = PessoaMapper.INSTANCE.from(dto);
        pessoaRepository.save(pessoa);
    }

    @Override
    public PessoaResponseDTO findById(@NonNull Long codigo) {
        return pessoaRepository.findById(codigo)
                .map(p -> {
                    var score = getScore(p.getScore());
                    var estados = getEstados(p.getRegiao());

                    return new PessoaResponseDTO(
                            p.getNome(),
                            p.getTelefone(),
                            p.getIdade(),
                            score,
                            estados
                    );
                })
                .orElseThrow(() -> new BusinessException(NO_CONTENT, StringUtils.EMPTY));
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return Optional.of(pessoaRepository.findAll())
                .filter(p -> !CollectionUtils.isEmpty(p))
                .orElseThrow(() -> new BusinessException(NO_CONTENT, StringUtils.EMPTY))
                .stream().map(p -> {
                    var score = getScore(p.getScore());
                    var estados = getEstados(p.getRegiao());

                    return new PessoaResponseDTO(
                            p.getNome(),
                            p.getCidade(),
                            p.getEstado(),
                            score,
                            estados
                    );
                }).collect(Collectors.toList());
    }

    private String getScore(Long score) {
        return scoreRepository.findScore(score).map(ScoreEntity::getDescricao)
                .orElse("Não identificado");
    }

    private Set<String> getEstados(String regiao) {
        return afinidadeRepository.findByRegiaoEEstados(regiao)
                .stream()
                .flatMap(a -> a.getEstados().stream())
                .map(AfinidadeEstadoEntity::getSiglaEstado)
                .collect(Collectors.toSet());
    }
}

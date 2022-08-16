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
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
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
    public void inserir(PessoaRequestDTO dto) {
        PessoaEntity pessoa = PessoaMapper.INSTANCE.from(dto);
        pessoaRepository.save(pessoa);
    }

    @Override
    public PessoaResponseDTO findById(Long codigo) {
        PessoaEntity pessoa = pessoaRepository.findById(codigo)
                .orElseThrow(() -> new BusinessException(NO_CONTENT, StringUtils.EMPTY));

        ScoreEntity score = getScore(pessoa.getScore());
        Set<String> estados = getEstados(pessoa.getRegiao());

        return new PessoaResponseDTO(
                pessoa.getNome(),
                pessoa.getTelefone(),
                pessoa.getIdade(),
                score.getDescricao(),
                estados
        );
    }

    @Override
    public List<PessoaResponseDTO> findAll() {
        return Optional.of(pessoaRepository.findAll())
                .filter(p -> !CollectionUtils.isEmpty(p))
                .orElseThrow(() -> new BusinessException(NO_CONTENT, StringUtils.EMPTY))
                .stream().map(p -> {
                    ScoreEntity score = getScore(p.getScore());
                    Set<String> estados = getEstados(p.getRegiao());

                    return new PessoaResponseDTO(
                            p.getNome(),
                            p.getCidade(),
                            p.getEstado(),
                            score.getDescricao(),
                            estados
                    );
                }).collect(Collectors.toList());
    }

    private ScoreEntity getScore(Long score) {
        return scoreRepository.findScore(score)
                .orElseThrow(() -> new BusinessException(NO_CONTENT, StringUtils.EMPTY));
    }

    private Set<String> getEstados(String regiao) {
        return afinidadeRepository.findByRegiaoEEstados(regiao)
                .stream()
                .flatMap(a -> a.getEstados().stream())
                .map(AfinidadeEstadoEntity::getSiglaEstado)
                .collect(Collectors.toSet());
    }
}

package com.serasa.desafio.service.impl;

import com.serasa.desafio.handler.exception.BusinessException;
import com.serasa.desafio.model.dto.AfinidadeRequestDTO;
import com.serasa.desafio.model.entity.AfinidadeEntity;
import com.serasa.desafio.model.entity.AfinidadeEstadoEntity;
import com.serasa.desafio.repository.IAfinidadeRepository;
import com.serasa.desafio.service.IAfinidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
@RequiredArgsConstructor
public class AfinidadeServiceImpl implements IAfinidadeService {
    private final IAfinidadeRepository afinidadeRepository;

    @Override
    public void inserir(@NotNull AfinidadeRequestDTO dto) {

        var novosEstados = new AtomicReference<>(dto.getEstados());
        final var afinidade = new AtomicReference<>(new AfinidadeEntity(dto.getRegiao(), novosEstados.get()));

        afinidadeRepository.findByRegiaoEEstados(dto.getRegiao()).ifPresent(estadosExistentes -> {
            novosEstados.set(validarEstados(novosEstados.get(), estadosExistentes.getEstados()));
            if(CollectionUtils.isEmpty(novosEstados.get())) {
                throw new BusinessException(UNPROCESSABLE_ENTITY, String.format("Todos os estados já estão cadastado para a região %s", dto.getRegiao()));
            }

            estadosExistentes.addEstados(novosEstados.get());
            afinidade.set(estadosExistentes);
        });
        afinidadeRepository.save(afinidade.get());
    }

    private Set<String> validarEstados(Set<String> estadosNovos, Set<AfinidadeEstadoEntity> estadosExistentes) {
        return estadosNovos.stream().filter(l1 ->
                !estadosExistentes.stream()
                        .map(AfinidadeEstadoEntity::getSiglaEstado)
                        .anyMatch(l2 -> l2.equalsIgnoreCase(l1))
        ).collect(Collectors.toSet());
    }
}

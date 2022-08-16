package com.serasa.desafio.service.impl;


import com.serasa.desafio.config.AbstractTest;
import com.serasa.desafio.handler.exception.BusinessException;
import com.serasa.desafio.model.dto.AfinidadeRequestDTO;
import com.serasa.desafio.model.entity.AfinidadeEntity;
import com.serasa.desafio.repository.IAfinidadeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.serasa.desafio.mock.MockTest.afinidadeEntity;
import static com.serasa.desafio.mock.MockTest.afinidadeRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AfinidadeServiceImplTest extends AbstractTest {

    private AutoCloseable autoCloseable;

    @Mock
    private IAfinidadeRepository afinidadeRepository;

    @Captor
    private ArgumentCaptor<AfinidadeEntity> afinidadeEntityCaptor;

    @InjectMocks
    private AfinidadeServiceImpl afinidadeService;

    @BeforeEach
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        afinidadeService = new AfinidadeServiceImpl(
                afinidadeRepository
        );
    }

    @Test
    @DisplayName("Deve retornar exception quando dto null")
    public void deveRetornarExceptionQaundoDtoNull() {
        assertThrows(NullPointerException.class, () -> afinidadeService.inserir(null));
    }

    @Test
    @DisplayName("Deve cadastrar regiao e estados com sucesso")
    public void deveCadastrarRegiaoEestadosComSucesso() {
        when(afinidadeRepository.save(any(AfinidadeEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(afinidadeRepository.findByRegiaoEEstados(anyString())).thenReturn(Optional.empty());

        AfinidadeRequestDTO dto = afinidadeRequest();
        afinidadeService.inserir(dto);

        verify(afinidadeRepository, times(1)).save(afinidadeEntityCaptor.capture());
        AfinidadeEntity afinidadeCaptorValue = afinidadeEntityCaptor.getValue();
        assertEquals(dto.getRegiao(), afinidadeCaptorValue.getRegiao());
        assertEquals(3, afinidadeCaptorValue.getEstados().size());
    }

    @Test
    @DisplayName("Deve cadastrar apenas estados inexistente para uma regiao cadastrada")
    public void deveCadastrarApenasEstadosInexistenteParaUmaRegiaoCadastrada() {
        when(afinidadeRepository.save(any(AfinidadeEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(afinidadeRepository.findByRegiaoEEstados(anyString())).thenReturn(Optional.of(afinidadeEntity()));

        AfinidadeRequestDTO dto = afinidadeRequest();
        Set<String> nl = new HashSet<>(dto.getEstados());
        nl.add("TST");
        dto.setEstados(nl);

        afinidadeService.inserir(dto);

        verify(afinidadeRepository, times(1)).save(afinidadeEntityCaptor.capture());
        AfinidadeEntity afinidadeCaptorValue = afinidadeEntityCaptor.getValue();
        assertEquals(dto.getRegiao(), afinidadeCaptorValue.getRegiao());
        assertEquals(1, afinidadeCaptorValue.getEstados().size());
    }

    @Test
    @DisplayName("Deve retornar exception quando realizar cadastro de estados existente para regiao existente")
    public void DeveRetornarExceptionQuandoRealizarCadastroEstadosExistenteParaRegiaoExistente() {
        when(afinidadeRepository.save(any(AfinidadeEntity.class))).thenAnswer(i -> i.getArguments()[0]);
        when(afinidadeRepository.findByRegiaoEEstados(anyString())).thenReturn(Optional.of(afinidadeEntity()));

        assertThrows(BusinessException.class, () -> afinidadeService.inserir(afinidadeRequest()));
    }

    @AfterEach
    public void afterEach() throws Exception {
        autoCloseable.close();
    }
}
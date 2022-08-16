package com.serasa.desafio.service.impl;

import com.serasa.desafio.config.AbstractTest;
import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.model.entity.ScoreEntity;
import com.serasa.desafio.repository.IScoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.serasa.desafio.mock.MockTest.scoreRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScoreServiceImplTest extends AbstractTest {

    @Mock
    private IScoreRepository scoreRepository;

    @Captor
    private ArgumentCaptor<ScoreEntity> scoreCaptor;

    @InjectMocks
    private ScoreServiceImpl scoreService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        scoreService = new ScoreServiceImpl(
                scoreRepository
        );
    }

    @Test
    @DisplayName("Deve cadastrar faixa score com sucesso")
    public void deveCadastrarFaixaScoreComSucesso() {
        when(scoreRepository.save(any(ScoreEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        ScoreRequestDTO dto = scoreRequest();
        scoreService.inserir(dto);

        verify(scoreRepository, times(1)).save(scoreCaptor.capture());
        ScoreEntity scoreCaptorValue = scoreCaptor.getValue();
        assertEquals(dto.getScoreDescricao(), scoreCaptorValue.getDescricao());
    }

    @AfterEach
    public void afterEach() throws Exception {
        autoCloseable.close();
    }

}
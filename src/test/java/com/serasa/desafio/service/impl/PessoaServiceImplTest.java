package com.serasa.desafio.service.impl;


import com.serasa.desafio.config.AbstractTest;
import com.serasa.desafio.handler.exception.BusinessException;
import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.dto.PessoaResponseDTO;
import com.serasa.desafio.model.entity.PessoaEntity;
import com.serasa.desafio.repository.IAfinidadeRepository;
import com.serasa.desafio.repository.IPessoaRepository;
import com.serasa.desafio.repository.IScoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.serasa.desafio.mock.MockTest.afinidadeEntity;
import static com.serasa.desafio.mock.MockTest.pessoaEntity;
import static com.serasa.desafio.mock.MockTest.pessoaRequest;
import static com.serasa.desafio.mock.MockTest.scoreEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PessoaServiceImplTest extends AbstractTest {

    private AutoCloseable autoCloseable;

    @Mock
    private IPessoaRepository pessoaRepository;

    @Mock
    private IAfinidadeRepository afinidadeRepository;

    @Mock
    private IScoreRepository scoreRepository;

    @Captor
    private ArgumentCaptor<PessoaEntity> pessoaEntityCaptor;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @BeforeEach
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        pessoaService = new PessoaServiceImpl(
                pessoaRepository,
                afinidadeRepository,
                scoreRepository
        );
    }

    @Nested
    class cadastarPessoaTest {

        @Test
        @DisplayName("Deve retornar exception quando dto null")
        public void deveRetornarExceptionQaundoDtoNull() {
            assertThrows(NullPointerException.class, () -> pessoaService.inserir(null));
        }

        @Test
        @DisplayName("Deve cadastrar pessoa com sucesso")
        public void deveCadastrarPessoaComSucesso() {
            when(pessoaRepository.save(any(PessoaEntity.class))).thenAnswer(i -> i.getArguments()[0]);

            PessoaRequestDTO dto = pessoaRequest();
            pessoaService.inserir(dto);

            verify(pessoaRepository, times(1)).save(pessoaEntityCaptor.capture());
            PessoaEntity pessoaCaptorValue = pessoaEntityCaptor.getValue();
            assertEquals(dto.getNome(), pessoaCaptorValue.getNome());
        }
    }

    @Nested
    class consultarPessoaTest {

        @Test
        @DisplayName("Deve retornar exception quando consultar pessoa por codigo null")
        public void deveRetornarExceptionQaundoDtoNull() {
            assertThrows(NullPointerException.class, () -> pessoaService.findById(null));
        }

        @Test
        @DisplayName("Deve consultar pessoa por codigo com sucesso")
        public void deveConsultarPessoaPorCodigoComSucesso() {
            PessoaEntity pessoaEntity = pessoaEntity();
            when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoaEntity));
            when(scoreRepository.findScore(anyLong())).thenReturn(Optional.of(scoreEntity()));
            when(afinidadeRepository.findByRegiaoEEstados(anyString())).thenReturn(Optional.of(afinidadeEntity()));

            PessoaResponseDTO pessoaRes = pessoaService.findById(1l);
            verify(pessoaRepository, times(1)).findById(anyLong());
            verify(scoreRepository, times(1)).findScore(anyLong());
            verify(afinidadeRepository, times(1)).findByRegiaoEEstados(anyString());

            assertEquals(pessoaEntity.getNome(), pessoaRes.getNome());
            assertEquals("Recomendável", pessoaRes.getScoreDescricao());
            assertTrue(!CollectionUtils.isEmpty(pessoaRes.getEstados()));
        }

        @Test
        @DisplayName("Deve retornar exception quando consultar pessoa inexistente")
        public void deveRetornarExceptionQuandoConsultarPessoaInexistente() {
            when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());
            assertThrows(BusinessException.class, () -> pessoaService.findById(1l));
        }

        @Test
        @DisplayName("Deve consultar pessoas com sucesso")
        public void deveConsultarPessoasComSucesso() {
            when(pessoaRepository.findAll()).thenReturn(Arrays.asList(pessoaEntity()));
            when(scoreRepository.findScore(anyLong())).thenReturn(Optional.of(scoreEntity()));
            when(afinidadeRepository.findByRegiaoEEstados(anyString())).thenReturn(Optional.of(afinidadeEntity()));


            List<PessoaResponseDTO> all = pessoaService.findAll();
            verify(pessoaRepository, times(1)).findAll();
            verify(scoreRepository, times(all.size())).findScore(anyLong());
            verify(afinidadeRepository, times(all.size())).findByRegiaoEEstados(anyString());

            assertTrue(!CollectionUtils.isEmpty(all));
        }

        @Test
        @DisplayName("Deve retornar exception quando nao existir pessoas")
        public void deveRetornarExceptionQuandoNaoExistirPessoas() {
            when(pessoaRepository.findAll()).thenReturn(new ArrayList<>());
            assertThrows(BusinessException.class, () -> pessoaService.findAll());
        }
    }

    @AfterEach
    public void afterEach() throws Exception {
        autoCloseable.close();
    }
}
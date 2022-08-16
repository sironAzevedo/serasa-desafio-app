package com.serasa.desafio.mock;

import com.serasa.desafio.model.dto.AfinidadeRequestDTO;
import com.serasa.desafio.model.dto.PessoaRequestDTO;
import com.serasa.desafio.model.dto.ScoreRequestDTO;
import com.serasa.desafio.model.entity.AfinidadeEntity;
import com.serasa.desafio.model.entity.PessoaEntity;
import com.serasa.desafio.model.entity.ScoreEntity;

import java.util.Set;

public final class MockTest {

    private MockTest(){}

    public static ScoreRequestDTO scoreRequest() {
        return ScoreRequestDTO
                .builder()
                .scoreDescricao("Recomendável")
                .inicial(701l)
                .faixaFinal(1000l)
                .build();
    }

    public static AfinidadeRequestDTO afinidadeRequest() {
        return AfinidadeRequestDTO
                .builder()
                .regiao("Sul")
                .estados(Set.of("PR", "RS", "SC"))
                .build();
    }

    public static PessoaRequestDTO pessoaRequest() {
        return PessoaRequestDTO
                .builder()
                .nome("Teste Silva")
                .telefone("11 99999-9999")
                .idade(99)
                .cidade("Cidade teste")
                .estado("SC")
                .regiao("Sul")
                .score(1000)
                .build();
    }

    public static PessoaEntity pessoaEntity() {
        return PessoaEntity
                .builder()
                .nome("Teste Silva")
                .telefone("11 99999-9999")
                .idade(99)
                .cidade("Cidade teste")
                .estado("SC")
                .regiao("Sul")
                .score(1000l)
                .build();
    }

    public static ScoreEntity scoreEntity() {
        return ScoreEntity
                .builder()
                .id(1l)
                .descricao("Recomendável")
                .inicial(701l)
                .faixaFinal(1000l)
                .build();
    }

    public static AfinidadeEntity afinidadeEntity() {
        return new AfinidadeEntity(afinidadeRequest().getRegiao(), afinidadeRequest().getEstados());
    }
}

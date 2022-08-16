package com.serasa.desafio.mock;

import com.serasa.desafio.model.dto.ScoreRequestDTO;

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
}

package com.serasa.desafio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaRequestDTO {

    @NotNull(message = "campo obrigatorio")
    private String nome;

    @NotNull(message = "campo obrigatorio")
    private String telefone;

    @Positive
    @NotNull(message = "campo obrigatorio")
    private Integer idade;

    @PositiveOrZero
    @NotNull(message = "campo obrigatorio")
    private Integer Score;

    @NotNull(message = "campo obrigatorio")
    private String cidade;

    @NotNull(message = "campo obrigatorio")
    private String estado;

    @NotNull(message = "campo obrigatorio")
    private String regiao;
}

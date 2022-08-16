package com.serasa.desafio.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Teste Silva", required = true)
    private String nome;

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "99 99999-9999", required = true)
    private String telefone;

    @Positive
    @NotNull(message = "campo obrigatorio")
    @Schema(description = "99", required = true)
    private Integer idade;

    @PositiveOrZero
    @NotNull(message = "campo obrigatorio")
    @Schema(description = "1000", required = true)
    private Integer score;

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "cidade teste", required = true)
    private String cidade;

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "SP", required = true)
    private String estado;

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "Sudeste", required = true)
    private String regiao;
}

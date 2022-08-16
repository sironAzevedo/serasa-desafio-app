package com.serasa.desafio.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScoreRequestDTO {

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "Recomendável", required = true)
    private String scoreDescricao;

    @NotNull(message = "campo obrigatorio")
    @Schema(description = "0", required = true)
    private Long inicial;

    @JsonProperty("final")
    @NotNull(message = "campo obrigatorio")
    @Schema(description = "200", required = true)
    private Long faixaFinal;
}

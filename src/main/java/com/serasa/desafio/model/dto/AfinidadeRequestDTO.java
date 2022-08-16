package com.serasa.desafio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AfinidadeRequestDTO {

    @NotNull(message = "campo obrigatorio")
    private String regiao;


    @NotNull(message = "campo obrigatorio")
    private Set<String> estados;

    public String getRegiao() {
        return StringUtils.capitalize(regiao);
    }
}

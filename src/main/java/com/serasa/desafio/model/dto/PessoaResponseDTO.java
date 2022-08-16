package com.serasa.desafio.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PessoaResponseDTO {
    private String nome;
    private String telefone;
    private Integer idade;
    private String scoreDescricao;
    private String cidade;
    private String estado;
    private Set<String> estados;

    public PessoaResponseDTO(String nome, String telefone, Integer idade, String scoreDescricao, Set<String> estados) {
        this.nome = nome;
        this.telefone = telefone;
        this. idade =  idade;
        this.scoreDescricao = scoreDescricao;
        this.estados = estados;
    }

    public PessoaResponseDTO(String nome, String cidade, String estado, String scoreDescricao, Set<String> estados) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado =  estado;
        this.scoreDescricao = scoreDescricao;
        this.estados = estados;
    }
}

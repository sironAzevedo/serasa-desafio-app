package com.serasa.desafio.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_SCORE")
public class ScoreEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    @NotNull(message = "campo obrigatorio")
    private String descricao;

    @Column(name = "INICIAL")
    @NotNull(message = "campo obrigatorio")
    private Long inicial;

    @Column(name = "FINAL")
    @NotNull(message = "campo obrigatorio")
    private Long faixaFinal;
}

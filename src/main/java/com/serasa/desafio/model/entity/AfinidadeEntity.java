package com.serasa.desafio.model.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_AFINIDADE")
public class AfinidadeEntity implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REGIAO", unique = true)
    private String regiao;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "afinidade", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Set<AfinidadeEstadoEntity> estados;

    public AfinidadeEntity(String regiao, Set<String> estados) {
        this.regiao = regiao;
        addEstados(estados);
    }

    public void addEstados(Set<String> novosEstados) {
        this.estados = novosEstados.stream().map(e -> AfinidadeEstadoEntity
                .builder()
                .afinidade(this)
                .siglaEstado(e)
                .build()).collect(Collectors.toSet());
    }
}

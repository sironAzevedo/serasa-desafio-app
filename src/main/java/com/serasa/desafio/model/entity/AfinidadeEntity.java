package com.serasa.desafio.model.entity;

import com.serasa.desafio.utils.GenerateUtil;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
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
    private Long id;

    @Column(name = "REGIAO", unique = true)
    private String regiao;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "afinidade", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<AfinidadeEstadoEntity> estados;

    public AfinidadeEntity(String regiao, Set<String> estados) {
        this.id = ObjectUtils.defaultIfNull(this.id, GenerateUtil.code());
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

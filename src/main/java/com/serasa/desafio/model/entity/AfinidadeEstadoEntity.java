package com.serasa.desafio.model.entity;

import com.serasa.desafio.utils.GenerateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString(exclude = {"afinidade"})
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_ESTADO")
public class AfinidadeEstadoEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_AFINIDADE", referencedColumnName = "id", nullable = false)
    private AfinidadeEntity afinidade;

    @Column(name = "DESC_ESTADO")
    private String siglaEstado;

    public static AfinidadeEstadoEntityBuilder builder() {
        return new AfinidadeEstadoEntityBuilder();
    }


    public static class AfinidadeEstadoEntityBuilder {
        private Long id;
        private AfinidadeEntity afinidade;
        private String siglaEstado;

        AfinidadeEstadoEntityBuilder() {
        }

        public AfinidadeEstadoEntityBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AfinidadeEstadoEntityBuilder afinidade(AfinidadeEntity afinidade) {
            this.afinidade = afinidade;
            return this;
        }

        public AfinidadeEstadoEntityBuilder siglaEstado(String siglaEstado) {
            this.siglaEstado = siglaEstado.toUpperCase();
            return this;
        }

        public AfinidadeEstadoEntity build() {
            return new AfinidadeEstadoEntity(GenerateUtil.code(), afinidade, siglaEstado);
        }

        public String toString() {
            return "AfinidadeEstadoEntity.AfinidadeEstadoEntityBuilder(id=" + this.id + ", afinidade=" + this.afinidade + ", siglaEstado=" + this.siglaEstado + ")";
        }
    }
}

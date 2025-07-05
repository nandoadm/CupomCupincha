package org.cupinchacupons.backend.modules.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loja_afiliado", schema = "public")
public class LojaAfiliadoEntity {
    @EmbeddedId
    private LojaAfiliadoId id;

    @MapsId("lojaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loja_id", nullable = false)
    private LojaEntity loja;

    @MapsId("afiliadoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "afiliado_id", nullable = false)
    private AfiliadoEntity afiliado;

}
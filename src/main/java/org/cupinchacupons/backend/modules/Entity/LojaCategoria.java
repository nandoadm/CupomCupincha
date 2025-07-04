package org.cupinchacupons.backend.modules.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loja_categoria", schema = "public")
public class LojaCategoria {
    @EmbeddedId
    private LojaCategoriaId id;

    @MapsId("lojaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loja_id", nullable = false)
    private LojaEntity loja;

    @MapsId("categoriaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaEntity categoria;

}
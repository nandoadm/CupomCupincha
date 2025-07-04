package org.cupinchacupons.backend.modules.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Embeddable
public class LojaCategoriaId implements Serializable {
    private static final long serialVersionUID = -2705233531551410605L;
    @NotNull
    @Column(name = "loja_id", nullable = false)
    private UUID lojaId;

    @NotNull
    @Column(name = "categoria_id", nullable = false)
    private UUID categoriaId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LojaCategoriaId entity = (LojaCategoriaId) o;
        return Objects.equals(this.lojaId, entity.lojaId) &&
                Objects.equals(this.categoriaId, entity.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lojaId, categoriaId);
    }

}
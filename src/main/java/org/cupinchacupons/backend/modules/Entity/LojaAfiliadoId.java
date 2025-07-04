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
public class LojaAfiliadoId implements Serializable {
    private static final long serialVersionUID = -5556060387960592600L;
    @NotNull
    @Column(name = "loja_id", nullable = false)
    private UUID lojaId;

    @NotNull
    @Column(name = "afiliado_id", nullable = false)
    private UUID afiliadoId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LojaAfiliadoId entity = (LojaAfiliadoId) o;
        return Objects.equals(this.afiliadoId, entity.afiliadoId) &&
                Objects.equals(this.lojaId, entity.lojaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(afiliadoId, lojaId);
    }

}
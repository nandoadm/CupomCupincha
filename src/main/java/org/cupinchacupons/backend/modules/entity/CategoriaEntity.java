package org.cupinchacupons.backend.modules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "categoria", schema = "public")
public class CategoriaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "descricao", nullable = false, length = 100)
    private String descricao;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "categoria")
    private Set<CupomEntity> cupoms = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "loja_categoria",
            joinColumns = @JoinColumn(name = "categoria_id"),
            inverseJoinColumns = @JoinColumn(name = "loja_id"))
    private Set<LojaEntity> lojas = new LinkedHashSet<>();

}
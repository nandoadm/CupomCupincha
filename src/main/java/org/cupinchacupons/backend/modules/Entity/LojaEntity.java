package org.cupinchacupons.backend.modules.Entity;

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
@Table(name = "loja", schema = "public")
public class LojaEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(max = 100)
    @NotNull
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Size(max = 255)
    @Column(name = "url")
    private String url;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "loja")
    private Set<CupomEntity> cupoms = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lojas")
    private Set<AfiliadoEntity> afiliados = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "lojas")
    private Set<CategoriaEntity> categorias = new LinkedHashSet<>();

}
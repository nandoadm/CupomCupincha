package org.cupinchacupons.backend.modules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cupom", schema = "public")
public class CupomEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name ="descricao")
    private String descricao;

    @Column(name = "titulo")
    private String titulo;

    @Size(max = 50)
    @NotNull
    @Column(name = "codigo", length = 50)
    private String codigo;


    @Column(name = "validade", nullable = false)
    private LocalDateTime validade;

//    @Column(name = "desconto", precision = 10, scale = 2)
//    private BigDecimal desconto;

    @Size(max = 255)
    @Column(name = "restricoes")
    private String restricoes;

    @Column(name = "created_At")
    @CreationTimestamp
    private LocalDateTime created_At;

    @Column(name = "ativo")
    private Integer ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private CategoriaEntity categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loja_id")
    private LojaEntity loja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "afiliado_id")
    private AfiliadoEntity afiliado;
}
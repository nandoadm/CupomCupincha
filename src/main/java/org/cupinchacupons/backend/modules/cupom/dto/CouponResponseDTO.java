package org.cupinchacupons.backend.modules.cupom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponResponseDTO {

    private UUID id;
    private String titulo;
    private String descricao;
    private String Codigo;
    private String validade;
    private String ativo;
    private String slug;
    private double desconto;
    private String restricao;
    private String createdAt;

    private String categoriaDescricao;
    private String afiliadoNome;
    private String lojaNome;
}

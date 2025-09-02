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
public class CouponRequestDTO {

    private String titulo;
    private String descricao;
    private String codigo;
    private String validade;
    private String ativo;
    private String restricoes;
    private String slug;
    private String desconto;
    private String categoriaDescricao;
    private String afiliadoNome;
    private String lojaNome;
    private String img_url;

    private UUID categoriaId;
    private UUID afiliadoId;
    private UUID lojaId;
}

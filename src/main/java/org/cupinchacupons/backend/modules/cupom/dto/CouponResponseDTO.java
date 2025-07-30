package org.cupinchacupons.backend.modules.cupom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponResponseDTO {

    private String titulo;
    private String descricao;
    private String Codigo;
    private String validade;
    private String ativo;
    private String categotiaDescricao;
}

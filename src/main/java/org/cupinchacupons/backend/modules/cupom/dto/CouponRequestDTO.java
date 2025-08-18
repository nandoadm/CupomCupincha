package org.cupinchacupons.backend.modules.cupom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CouponRequestDTO {

    private Integer ativo;
    private String codigo;
    private String desconto;
    private String restricoes;
    private Timestamp validade;
    private String descricao;
    private String slug;
    private String titulo;


}

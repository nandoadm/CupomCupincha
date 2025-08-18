package org.cupinchacupons.backend.modules.afiliado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfiliadoResponseDTO {

    private String cnpj;
    private String createdAt;
    private String nome;
}

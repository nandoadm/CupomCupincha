package org.cupinchacupons.backend.modules.afiliado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AfiliadoResponseDTO {
    private UUID id;

    private String cnpj;
    private String createdAt;
    private String nome;
}

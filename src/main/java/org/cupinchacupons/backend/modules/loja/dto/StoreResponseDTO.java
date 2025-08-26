package org.cupinchacupons.backend.modules.loja.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreResponseDTO {
    private UUID id;
    private String nome;
    private String url;
}

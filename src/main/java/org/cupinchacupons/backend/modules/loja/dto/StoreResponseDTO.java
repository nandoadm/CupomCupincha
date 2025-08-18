package org.cupinchacupons.backend.modules.loja.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StoreResponseDTO {
    private String nome;
    private String url;
}

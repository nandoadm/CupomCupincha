package org.cupinchacupons.frontend.modules.admin.categoria.service;


import org.cupinchacupons.backend.modules.categoria.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListCategoriaService {

    private final WebClient webClient;

    public ListCategoriaService(WebClient.Builder builder, @Value("$backend.base.url") String baseUrl) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public List<CategoriaResponseDTO> listCategoria(String filtro){
        List<CategoriaResponseDTO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/listar-categoria")
                        .queryParam("filtro", filtro)
                        .build())
                .retrieve()
                .bodyToFlux(CategoriaResponseDTO.class)
                .collectList()
                .block();
        System.out.println("Retorno" + result);
        return result;

    }

}

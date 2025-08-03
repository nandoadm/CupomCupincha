package org.cupinchacupons.frontend.modules.user.usecase;


import org.cupinchacupons.backend.modules.categoria.dto.CategoriaResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListCategoriaService {

    private final WebClient webClient;

    public ListCategoriaService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080") // sempre aponta para a API backend
                .build();
    }

    public List<CategoriaResponseDTO> execute(String descricao){
        var result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/listarCategoria")
                        .queryParam("filter", descricao)
                        .build())
                .retrieve()
                .bodyToFlux(CategoriaResponseDTO.class)
                .collectList()
                .block();
        System.out.println("Retorno" + result);
        return result;

    }

}

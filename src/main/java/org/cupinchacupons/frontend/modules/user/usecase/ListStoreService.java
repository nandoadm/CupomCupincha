package org.cupinchacupons.frontend.modules.user.usecase;


import org.cupinchacupons.backend.modules.loja.dto.StoreResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListStoreService {

    private final WebClient webClient;

    public ListStoreService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080") // sempre aponta para a API backend
                .build();
    }

    public List<StoreResponseDTO> execute(String filtro){
        var result = webClient.get()
                .uri(
                        uriBuilder -> uriBuilder
                            .path("/api/listarLoja")
                                .queryParam("filtro", filtro)
                                .build())
                .retrieve()
                .bodyToFlux(StoreResponseDTO.class)
                .collectList()
                .block();

        System.out.println("Retorno da Loja" + result);
        return result;
    }
}

package org.cupinchacupons.frontend.modules.admin.afiliado.service;

import org.cupinchacupons.backend.modules.afiliado.dto.AfiliadoResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListAfiliadoService {

    private final WebClient webClient;

    public ListAfiliadoService(WebClient.Builder builder, @Value("${backend.api.base-url}") String baseUrl) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public List<AfiliadoResponseDTO> listAfiliados(String filtro) {
        List<AfiliadoResponseDTO> afiliados = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/listar-afiliado")
                        .queryParam("filtro", filtro)
                        .build())
                .retrieve()
                .bodyToFlux(AfiliadoResponseDTO.class)
                .collectList()
                .block();

        System.out.println("Retorno do Filtro: " + afiliados);
        return afiliados;
    }
}

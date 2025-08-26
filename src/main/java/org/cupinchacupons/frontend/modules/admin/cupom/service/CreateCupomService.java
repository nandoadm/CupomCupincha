package org.cupinchacupons.frontend.modules.admin.cupom.service;

import org.cupinchacupons.backend.modules.cupom.dto.CouponRequestDTO;
import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateCupomService {

    private final WebClient webClient;

    public CreateCupomService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080") // URL do backend
                .build();
    }

    public CouponResponseDTO createCupom(CouponRequestDTO dto) {
        CouponResponseDTO result = webClient.post()
                .uri("/api/create-cupom")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(CouponResponseDTO.class)
                .block();

        if (result == null) {
            throw new RuntimeException("Erro ao criar cupom");
        }

        System.out.println("Cupom criado: " + result);
        return result;
    }
}

package org.cupinchacupons.frontend.modules.admin.cupom.service;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateCupomService {

    private final WebClient webClient;

    public CreateCupomService(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("http://localhost:8080") // sempre aponta para a API backend
                .build();
    }

    public CouponResponseDTO createCupom(CouponResponseDTO couponResponseDTO) {
        CouponResponseDTO result = webClient.post()
                .uri("/api/cupom")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(couponResponseDTO)
                .retrieve()
                .bodyToMono(CouponResponseDTO.class)
                .block();

        if (result == null) {
            throw new RuntimeException("Erro ao criar cupom");
        }

        System.out.println("Retorno do cupom: " + result);
        return result;
    }

}

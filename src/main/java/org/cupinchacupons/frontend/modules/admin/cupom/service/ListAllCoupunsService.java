package org.cupinchacupons.frontend.modules.admin.cupom.service;

import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ListAllCoupunsService {

    private final WebClient webClient;

    public ListAllCoupunsService(WebClient.Builder builder, @Value("$backend.base.url") String baseUrl) {
        this.webClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public List<CouponResponseDTO> listCoupouns(String titulo) {

        List<CouponResponseDTO> coupons = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/listar")
                        .queryParam("filtro", titulo)
                        .build())
                .retrieve()
                .bodyToFlux(CouponResponseDTO.class)
                .collectList()
                .block();

        System.out.println("Retorno do Filtro: " + coupons);
        return coupons;
    }
}


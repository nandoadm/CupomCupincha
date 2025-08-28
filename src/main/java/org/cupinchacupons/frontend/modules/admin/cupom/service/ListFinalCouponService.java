package org.cupinchacupons.frontend.modules.admin.cupom.service;

import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ListFinalCouponService {

    private final WebClient webClient;

    public ListFinalCouponService(WebClient webClient) {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

    }

    public Object listFinalCoupons() {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/list-final-coupon")
                        .build()
                )

                .retrieve()
                .bodyToMono(CouponResponseDTO.class)
                .block();
        return null;
    }
}

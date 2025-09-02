package org.cupinchacupons.frontend.modules.admin.cupom.service;

import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ListFinalCouponService {

    private final WebClient webClient;

    public ListFinalCouponService(WebClient.Builder builder, @Value("${backend.api.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
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

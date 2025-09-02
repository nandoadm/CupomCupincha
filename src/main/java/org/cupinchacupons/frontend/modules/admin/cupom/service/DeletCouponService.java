package org.cupinchacupons.frontend.modules.admin.cupom.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Service
public class DeletCouponService {

    private final WebClient webClient;
    private final String baseUrl;

    public DeletCouponService(WebClient.Builder builder, @Value("${backend.base.url}") String baseUrl) {
        this.webClient = builder.baseUrl(baseUrl).build();
        this.baseUrl = baseUrl;
    }

    public String deleteCoupon(UUID id) {

        try {

        webClient.delete()
                .uri(baseUrl + "/api/delete-coupon/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() >= 400, response -> {
                    throw new RuntimeException("Erro ao deletar cupom: " + response.statusCode());
                })
                .toBodilessEntity()
                .block();
        return "success";
        }catch (Exception e){
            return "error";
        }
    }
}
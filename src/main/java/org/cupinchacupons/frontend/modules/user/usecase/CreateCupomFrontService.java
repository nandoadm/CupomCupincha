package org.cupinchacupons.frontend.modules.user.usecase;


import org.cupinchacupons.backend.modules.cupom.dto.CouponResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CreateCupomFrontService {

    private final WebClient webClient;

    public CreateCupomFrontService(WebClient webClient) {
        this.webClient = WebClient.builder().build();
    }

    public CouponResponseDTO createCupom(CouponResponseDTO couponResponseDTO){
        var result = webClient.post()
                .uri("http://localhost:8080/api/cupom")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(couponResponseDTO)
                .retrieve()
                .bodyToMono(couponResponseDTO.getClass())
                .block();

        System.out.println("Retorno do cupom" + result);
        return result;
    }

}

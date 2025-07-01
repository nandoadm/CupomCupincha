package org.cupinchacupons.frontend.modules.usecase;

import org.cupinchacupons.backend.modules.dto.AuthUserRequestDTO;
import org.cupinchacupons.frontend.modules.dto.TokenDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserService {
    private final WebClient webClient;

    public UserService(WebClient webClient) {
        this.webClient = WebClient.builder().build();
    }


    public TokenDTO login(String username, String password) {
        var authUserRequestDTO = new AuthUserRequestDTO(username, password);

        var result = webClient.post()
                .uri("http://localhost:8080/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(authUserRequestDTO)
                .retrieve()
                .bodyToMono(TokenDTO.class)
                .block();
        System.out.println("Retorno da requisição " + result);
        return result;

    }
}

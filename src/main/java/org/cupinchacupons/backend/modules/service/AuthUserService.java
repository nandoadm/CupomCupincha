package org.cupinchacupons.backend.modules.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.cupinchacupons.backend.modules.repository.UserRepository;
import org.cupinchacupons.backend.modules.dto.AuthUserRequestDTO;
import org.cupinchacupons.backend.modules.dto.AuthUserResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AuthUserService {

    @Value("${security.token.secret}")
    private String secretKey;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDTO execute(AuthUserRequestDTO authUserRequestDTO) {
        var user = this.userRepository.findByUsername(authUserRequestDTO.username()).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado")
        );

        var passwordMatches = passwordEncoder.matches(authUserRequestDTO.password(), user.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("Senha incorreta");
        }
        // a estrutura do JWT é formada por 3 partes
        // Header, Payload, Signature

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plusSeconds(1800);

        var roles = List.of("ADMIN");

        var token = JWT.create()
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles",roles)
                .sign(algorithm);

        return AuthUserResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toString())
                .role(roles)
                .build();

    }
}

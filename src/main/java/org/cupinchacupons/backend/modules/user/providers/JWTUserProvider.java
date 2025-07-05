package org.cupinchacupons.backend.modules.user.providers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTUserProvider {

    @Value("security.token.secret")
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var decodedToken = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return decodedToken;
        } catch (JWTVerificationException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return null;
        }
    }

}

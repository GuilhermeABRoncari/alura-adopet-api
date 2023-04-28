package br.com.alura.adopet.adopet.infra.security;

import br.com.alura.adopet.adopet.domain.entity.Shelter;
import br.com.alura.adopet.adopet.domain.entity.Tutor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    private static final String ERROR_MESSAGE = "Error to generate JWT.";

    public String generateToken(Tutor tutor) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API adopet")
                    .withSubject(tutor.getEmail())
                    .withClaim("id", tutor.getId())
                    .withExpiresAt(expiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(ERROR_MESSAGE, exception);
        }
    }

    public String generateToken(Shelter shelter) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API adopet")
                    .withSubject(shelter.getEmail())
                    .withClaim("id", shelter.getId())
                    .withExpiresAt(expiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException(ERROR_MESSAGE, exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API adopet")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token", exception);
        }
    }

    private Instant expiration() {
        return OffsetDateTime.now().plusHours(6).toInstant();
    }
}

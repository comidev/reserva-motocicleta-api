package comidev.ejercicio01_back.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpiration;

    public String createToken(String username) {
        final long EXPIRES_MILLI = Long.parseLong(jwtExpiration);
        return JWT.create()
                .withIssuer("comidev") // Quién generó el Token
                .withIssuedAt(new Date()) // Fecha de creación
                .withNotBefore(new Date()) // Fecha de validez
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRES_MILLI)) // Fecha de invalidez
                .withClaim("username", username) // Claim guarda datos: asociamos el username
                .sign(Algorithm.HMAC256(jwtSecret)); // Firma
    }

    public String username(String token) {
        return verify(token).getClaim("username").asString();
    }

    private DecodedJWT verify(String token) {
        if (!isBearer(token)) {
            throw new JwtException("It is NOT Bearer");
        }
        try {
            String tokenContent = token.substring(7);
            return JWT.require(Algorithm.HMAC256(jwtSecret))
                    .withIssuer("comidev").build()
                    .verify(tokenContent);
        } catch (Exception e) {
            throw new JwtException("Token is NOT valid");
        }
    }

    public boolean isBearer(String tokenBearer) {
        return tokenBearer != null && tokenBearer.startsWith("Bearer ");
    }
}

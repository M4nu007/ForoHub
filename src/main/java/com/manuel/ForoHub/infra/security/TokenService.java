package com.manuel.ForoHub1.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.manuel.ForoHub1.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    private static final String ISSUER = "API foro.hub";

    public String generarToken(Usuario usuario) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el token JWT", exception);
        }
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {

        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw  new RuntimeException("Token JWT invalido o expirado!"+exception);
        }
    }

}
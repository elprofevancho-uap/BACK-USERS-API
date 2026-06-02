package com.uap.user.usecase.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.uap.user.usecase.JwtService;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    // ESTA CLAVE DEBE SER SECRETA Y GUARDARSE EN UN ENTORNOS SEGUROS (application.properties)
    private static final String SECRET_KEY = "MiClaveSecretaSuperSeguraQueNadieDebeSaber";
    private static final String ISSUER = "MiApiSpringBoot";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    // 1. GENERAR EL TOKEN
    public String generarToken(String email, String externalId) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(email) // El usuario principal
                .withClaim("userId", externalId) // Datos extra que quieras meter (Claims)
                .withIssuedAt(new Date()) // Fecha de creación
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
                .sign(algorithm); // Firmar el token con nuestro algoritmo secreto
    }

    // 2. VALIDAR Y LEER EL TOKEN
    public String extraerEmail(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token); // Si el token fue alterado o expiró, aquí lanzará una excepción

        return decodedJWT.getSubject();
    }
}

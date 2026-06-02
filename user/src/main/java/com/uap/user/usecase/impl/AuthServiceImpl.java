package com.uap.user.usecase.impl;

import com.uap.user.dto.entity.User;
import com.uap.user.dto.model.LoginRequest;
import com.uap.user.dto.model.LoginResponse;
import com.uap.user.exception.UnauthorizedException;
import com.uap.user.repository.UserRepository;
import com.uap.user.usecase.AuthService;
import com.uap.user.usecase.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    public static final String CREDENTIAL_INCORRECT = "Credenciales incorrectas";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        log.info("Iniciando el login - {} {}", request.getEmail(), request.getPassword());
        // 1. Buscar al usuario por email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnauthorizedException(CREDENTIAL_INCORRECT));

        log.info("Usuario encontrado: {}", user.getEmail());
        // 2. Verificar si la contraseña coincide con el hash de la BD
        if (!passwordEncoder.matches(request.getPassword().trim(), user.getPassword())) {
            log.error("Password incorrectas");
            throw new UnauthorizedException(CREDENTIAL_INCORRECT);
        }
        log.info("Credenciales correctas");

        // 3. Generar un token
        String token = jwtService.generarToken(user.getEmail(), user.getExternalId().toString());

        log.info("Token generado: {}", token);

        // 4. Retornar la respuesta exitosa
        return new LoginResponse(token, user.getEmail());
    }
}

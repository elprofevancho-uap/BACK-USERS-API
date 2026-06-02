package com.uap.user.controller;

import com.uap.user.dto.model.LoginRequest;
import com.uap.user.dto.model.LoginResponse;
import com.uap.user.exception.UnauthorizedException;
import com.uap.user.usecase.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        log.info("Iniciando el login");
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException e) {
            // Si las credenciales fallan, devolvemos 401 Unauthorized
            return ResponseEntity.status(401).build();
        }
        catch (Exception e) {
            // Esto va a forzar la escritura del error real en la consola de IntelliJ
            System.out.println("====== 🚨 EXCEPCIÓN DETECTADA EN JAVA ======");
            e.printStackTrace();
            System.out.println("=============================================");

            return ResponseEntity.internalServerError().build();
        }
    }
}

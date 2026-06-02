package com.uap.user.dto.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data // Genera getters, setters y constructores con Lombok
public class LoginRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

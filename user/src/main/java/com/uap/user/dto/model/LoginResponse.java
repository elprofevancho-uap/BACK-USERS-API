package com.uap.user.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private String email;
    // Puedes agregar más campos como: private List<String> roles;
}
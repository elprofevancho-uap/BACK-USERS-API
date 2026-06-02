package com.uap.user.usecase;

import com.uap.user.dto.model.LoginRequest;
import com.uap.user.dto.model.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    LoginResponse login(LoginRequest request);
}

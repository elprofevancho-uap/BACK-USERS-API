package com.uap.user.usecase;

import org.springframework.stereotype.Service;

@Service
public interface JwtService {

   String generarToken(String email, String externalId);
   String extraerEmail(String token);
}

package com.uap.user.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    // Al llamar a este endpoint, se limpia toda la caché llamada "users"
    @PostMapping("/cache/clear-users")
    @CacheEvict(value = "users", allEntries = true)
    public ResponseEntity<String> clearAllUsersCache() {
        return ResponseEntity.ok("La caché 'users' ha sido reiniciada por completo.");
    }
}

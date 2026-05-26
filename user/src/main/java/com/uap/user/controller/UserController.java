package com.uap.user.controller;

import com.uap.user.dto.entity.User;
import com.uap.user.dto.model.UserRequest;
import com.uap.user.usecase.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Ruta base para todos los endpoints de usuarios
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * Endpoint para obtener la lista completa de usuarios.
     * Ruta: GET http://localhost:8080/api/users/list
    */
    @GetMapping("/list")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam @NotNull String name) {
        List<User> users = userService.findAllUsers(name);
        
        // Es una buena práctica envolver la respuesta en un ResponseEntity
        // para manejar correctamente los estados HTTP (en este caso, 200 OK)
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  @Valid UserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }


}

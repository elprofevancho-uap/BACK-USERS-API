package com.uap.user.controller;

import com.uap.user.dto.model.UserRequest;
import com.uap.user.dto.model.UserResponse;
import com.uap.user.exception.NotFoundException;
import com.uap.user.usecase.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        // Es una buena práctica envolver la respuesta en un ResponseEntity
        // para manejar correctamente los estados HTTP (en este caso, 200 OK)
        return ResponseEntity.ok(userService.findAllUsers(Optional.empty()));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody  @Valid UserRequest request){
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody @Valid UserRequest request){
        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id){
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}

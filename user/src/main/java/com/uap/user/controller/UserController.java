package com.uap.user.controller;

import com.uap.user.dto.entity.User;
import com.uap.user.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users") // Ruta base para todos los endpoints de usuarios
public class UserController {

     @GetMapping
    public ResponseEntity<String> getAllUsers() {
       return ResponseEntity.ok("users");
    }




    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para obtener la lista completa de usuarios.
     * Ruta: GET http://localhost:8080/api/users
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        
        // Es una buena práctica envolver la respuesta en un ResponseEntity
        // para manejar correctamente los estados HTTP (en este caso, 200 OK)
        return ResponseEntity.ok("users");
    }
*/
   
}

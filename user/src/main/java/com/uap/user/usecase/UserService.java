package com.uap.user.usecase;

import com.uap.user.dto.entity.User;
import com.uap.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // Inyectamos el repositorio para poder usar sus métodos
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca y devuelve todos los usuarios de la base de datos.
     * @return Lista de entidades User
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}

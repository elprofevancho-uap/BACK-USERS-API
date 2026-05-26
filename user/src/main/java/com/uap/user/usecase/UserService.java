package com.uap.user.usecase;

import com.uap.user.dto.entity.User;
import com.uap.user.dto.model.UserRequest;
import com.uap.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    //@Cacheable("users_list") //Redis por detras
    public List<User> findAllUsers(String fullName) {
        return userRepository.findByFullName(fullName);
    }

    public User createUser(UserRequest request) {
        return userRepository.save(User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build());
    }
}

package com.uap.user.usecase.impl;

import com.uap.user.dto.entity.User;
import com.uap.user.dto.mapper.UserMapper;
import com.uap.user.dto.model.UserRequest;
import com.uap.user.dto.model.UserResponse;
import com.uap.user.exception.NotFoundException;
import com.uap.user.repository.UserRepository;
import com.uap.user.usecase.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j //lib de logging
@RequiredArgsConstructor // <--- Lombok genera el constructor para todos los campos 'final'
public class UserServiceImpl implements UserService {

    // Inyectamos el repositorio para poder usar sus métodos
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    /**
     * Busca y devuelve todos los usuarios de la base de datos.
     * @return Lista de entidades User
     */
    @Cacheable("users") //Redis por detras
    public List<UserResponse> findAllUsers(Optional<String> fullName) {
        log.info("Buscando los usuarios");
        return findUsers(fullName).stream().map(userMapper::toDTO).toList();
    }

    @Cacheable("user_id")
    public UserResponse findById(String id) {
        log.info("Buscando el usuario con id: {}", id);
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new NotFoundException(
                        String.format("User id %s not found", id)));
        return userMapper.toDTO(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public UserResponse createUser(UserRequest request) {
        log.info("Creando el usuario con email: {}", request.getEmail());
        User user = userRepository.save(User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return userMapper.toDTO(user);
    }

    @CachePut(value = "user_id", key = "#id")
    public UserResponse updateUser(String id, UserRequest request) {
        log.info("Actualizando el usuario con id: {}", id);
        User user = userRepository.save(User.builder()
                .externalId(UUID.fromString(id))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());
        return userMapper.toDTO(user);
    }

    @CacheEvict(value = "user_id", key = "#id")
    public void delete(String id) {
        log.info("Eliminando el usuario con id: {}", id);
        userRepository.deleteById(UUID.fromString(id));
    }

    private List<User> findUsers(Optional<String> fullName) {
        if(fullName.isPresent()) {
            log.info("Buscando usuarios por fullName");
            return userRepository.findByFullName(fullName.get());
        }
        log.info("Buscando todos los usuarios");
        return userRepository.findAll();
    }
}

package com.uap.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uap.user.dto.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // Al extender JpaRepository<Entidad, Tipo_De_ID> ya tienes el CRUD completo.
    // Podés agregar métodos personalizados acá si lo necesitas en el futuro.
    List<User> findByFullName(String fullName);
    Optional<User> findByEmail(String email);
}
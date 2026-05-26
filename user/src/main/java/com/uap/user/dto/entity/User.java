package com.uap.user.dto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "users") // Se recomienda usar el plural para el nombre de la tabla en Postgres
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID autoincremental (SERIAL/BIGSERIAL en Postgres)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100) // El email no puede ser nulo y debe ser único
    private String email;
}
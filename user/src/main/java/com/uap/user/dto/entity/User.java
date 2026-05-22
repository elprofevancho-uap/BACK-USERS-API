package com.uap.user.dto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") // Se recomienda usar el plural para el nombre de la tabla en Postgres
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera un ID autoincremental (SERIAL/BIGSERIAL en Postgres)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100) // El email no puede ser nulo y debe ser único
    private String email;

    // Constructor vacío obligatorio por la especificación de JPA
    public User() {
    }

    // Constructor con parámetros (útil para crear instancias rápidamente)
    public User(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
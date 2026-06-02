package com.uap.user.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") // Se recomienda usar el plural para el nombre de la tabla en Postgres
public class User {

    @Id
    @Column(name = "external_id", updatable = false, nullable = false)
    private UUID externalId;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100) // El email no puede ser nulo y debe ser único
    private String email;

    @Column(name = "password", nullable = false, length = 300)
    private String password;

    @CreatedDate // <--- Spring Boot asigna la fecha automáticamente al crear
    @Column(name = "created_date", updatable = false, nullable = false)
    private OffsetDateTime createdDate;

    @LastModifiedDate // <--- Spring Boot actualiza la fecha automáticamente en cada UPDATE
    @Column(name = "updated_date", nullable = false)
    private OffsetDateTime updateDate;

    @PrePersist
    private void preCreate() {
        this.externalId = UUID.randomUUID();
    }
}
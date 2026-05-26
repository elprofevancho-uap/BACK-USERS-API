-- 1. Creamos la tabla con la estructura exacta que Hibernate quiere
CREATE TABLE IF NOT EXISTS users (
                                     id BIGSERIAL PRIMARY KEY,
                                     full_name VARCHAR(150) NOT NULL,
    email VARCHAR(100)
    );

-- 2. Insertamos usando los nombres de columna correctos (con guión bajo)
INSERT INTO users (full_name, email) VALUES ('Juan Pérez', 'juan.perez@email.com');
INSERT INTO users (full_name, email) VALUES ('María López', 'maria.lopez@email.com');
INSERT INTO users (full_name, email) VALUES ('Carlos Gómez', 'carlos.gomez@email.com');

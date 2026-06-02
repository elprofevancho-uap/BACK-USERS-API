DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    full_name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    external_id uuid NOT NULL,
    password text COLLATE pg_catalog."default" NOT NULL,
    created_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_date timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT pk_usuarios_external_id PRIMARY KEY (external_id)
);

-- 2. Insertamos usando los nombres de columna correctos (con guión bajo)
INSERT INTO users (full_name, email, external_id, password)
VALUES
    ('Alejandro Gómez', 'alejandro.gomez@email.com', gen_random_uuid(), '$2a$12$eImiTXuWVxfM37uY4JANjOL.oM9VdWg7gMOb4B8D1eO8k1Tj6tU6W'), -- password123
    ('María Laura Fernández', 'maria.fernandez@email.com', gen_random_uuid(), '$2a$12$6yH2vX5K9p8Nn9sQm4RfO.u1iA5mRz3EwXy2bVv1cO4e3tY5a7s6d'), -- admin2026
    ('Carlos Mendoza', 'carlos.mendoza@email.com', gen_random_uuid(), '$2a$12$9Kj8h7g6f5e4d3c2b1a0uO.pQeRtYuIiOoPpAaSsDdFfGgHhJjKkL'); -- secret_pass

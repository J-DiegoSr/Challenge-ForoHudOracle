CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    status VARCHAR(50),
    autor VARCHAR(255),
    curso VARCHAR(255),
    UNIQUE KEY idx_unique_title_message (titulo, mensaje(255))
);
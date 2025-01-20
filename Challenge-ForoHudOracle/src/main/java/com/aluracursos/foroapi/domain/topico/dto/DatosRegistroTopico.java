package com.aluracursos.foroapi.domain.topico.dto;

import com.aluracursos.foroapi.domain.topico.enumeraciones.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Estado status,
        @NotBlank
        String autor,
        @NotNull
        Curso curso
) {
    public LocalDateTime fecha_creacion() {
        return LocalDateTime.now();
    }
}

package com.aluracursos.foroapi.domain.topico.dto;

import com.aluracursos.foroapi.domain.topico.enumeraciones.*;
import jakarta.validation.constraints.*;

public record DatosActualizarTopico(
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
}

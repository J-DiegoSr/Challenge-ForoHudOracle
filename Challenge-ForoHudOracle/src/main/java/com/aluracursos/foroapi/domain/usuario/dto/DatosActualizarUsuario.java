package com.aluracursos.foroapi.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarUsuario(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}

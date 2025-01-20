package com.aluracursos.foroapi.domain.usuario.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
        @NotBlank
        String login,
        @NotBlank
        String clave
) {
}

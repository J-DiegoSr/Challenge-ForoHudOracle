package com.aluracursos.foroapi.domain.usuario.dto;

import com.aluracursos.foroapi.domain.usuario.clases.Usuario;

public record DatosDetalleUsuario (
        String login,
        String clave
) {
    public DatosDetalleUsuario(Usuario usuario) {
        this(
                usuario.getLogin(),
                usuario.getClave()
        );
    }
}

package com.aluracursos.foroapi.domain.usuario.dto;

import com.aluracursos.foroapi.domain.usuario.clases.Usuario;

public record DatosListadoUsuario(
        Long id,
        String login,
        String clave
) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getClave());
    }
}

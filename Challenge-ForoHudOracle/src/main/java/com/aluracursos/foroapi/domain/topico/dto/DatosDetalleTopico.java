package com.aluracursos.foroapi.domain.topico.dto;

import com.aluracursos.foroapi.domain.topico.enumeraciones.*;
import com.aluracursos.foroapi.domain.topico.clases.Topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Estado status,
        String autor,
        Curso curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}


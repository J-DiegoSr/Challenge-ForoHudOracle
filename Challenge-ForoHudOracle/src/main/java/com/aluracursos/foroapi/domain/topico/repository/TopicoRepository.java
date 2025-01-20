package com.aluracursos.foroapi.domain.topico.repository;

import com.aluracursos.foroapi.domain.topico.clases.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}

package com.aluracursos.foroapi.domain.usuario.repository;

import com.aluracursos.foroapi.domain.usuario.clases.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String username);
    boolean existsByLogin(String login);
}

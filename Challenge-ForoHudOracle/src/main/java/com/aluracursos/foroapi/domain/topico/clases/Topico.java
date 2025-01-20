package com.aluracursos.foroapi.domain.topico.clases;

import com.aluracursos.foroapi.domain.topico.enumeraciones.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado status;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;

}

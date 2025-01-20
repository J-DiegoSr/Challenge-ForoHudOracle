package com.aluracursos.foroapi.controller;

import com.aluracursos.foroapi.domain.topico.clases.Topico;
import com.aluracursos.foroapi.domain.topico.dto.*;
import com.aluracursos.foroapi.domain.topico.repository.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    private final PagedResourcesAssembler<DatosListadoTopico> assembler;

    public TopicoController(PagedResourcesAssembler<DatosListadoTopico> assembler, TopicoRepository topicoRepository) {
        this.assembler = assembler;
        this.topicoRepository = topicoRepository;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DatosListadoTopico>>> listadoTopicos(@PageableDefault(size = 1) Pageable paginacion) {
        Page<DatosListadoTopico> topicosPage = topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        PagedModel<EntityModel<DatosListadoTopico>> pagedModel = assembler.toModel(topicosPage);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<String> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {

        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un Topico con este titulo y mensaje");
        }

        Topico topico = new Topico();
        topico.setTitulo(datosRegistroTopico.titulo());
        topico.setMensaje(datosRegistroTopico.mensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(datosRegistroTopico.status());
        topico.setAutor(datosRegistroTopico.autor());
        topico.setCurso(datosRegistroTopico.curso());

        topicoRepository.save(topico);
        return ResponseEntity.ok("Topico Creado Correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTopico(@PathVariable Long id, @Valid @RequestBody DatosActualizarTopico datosActualizarTopico) {
        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Topico no encontrado"));


        if (topicoRepository.existsByTituloAndMensaje(datosActualizarTopico.titulo(), datosActualizarTopico.mensaje())) {
            return ResponseEntity.badRequest().body("Ya existe un Topico con este titulo y mensaje");
        }

        topico.setTitulo(datosActualizarTopico.titulo());
        topico.setMensaje(datosActualizarTopico.mensaje());
        topico.setStatus(datosActualizarTopico.status());
        topico.setAutor(datosActualizarTopico.autor());
        topico.setCurso(datosActualizarTopico.curso());

        topicoRepository.save(topico);
        return ResponseEntity.ok("Topico actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        if (topicoRepository.existsById(id)) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok("Topico eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerDetalleTopico(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("ser requiere un ID ");
        }

        Topico topico = topicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Topico no encontrado"));
        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);
        return ResponseEntity.ok(datosDetalleTopico);
    }
}

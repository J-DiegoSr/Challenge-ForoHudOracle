package com.aluracursos.foroapi.controller;

import com.aluracursos.foroapi.domain.usuario.clases.Usuario;
import com.aluracursos.foroapi.domain.usuario.dto.*;
import com.aluracursos.foroapi.domain.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    private final PagedResourcesAssembler<DatosListadoUsuario> assembler;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, PagedResourcesAssembler<DatosListadoUsuario> assembler) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.assembler = assembler;
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<DatosListadoUsuario>>> listadoUsuarios(@PageableDefault(size = 1) Pageable paginacion) {
        Page<DatosListadoUsuario> usuariosPage = usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
        PagedModel<EntityModel<DatosListadoUsuario>> pagedModel = assembler.toModel(usuariosPage);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario) {

        if (usuarioRepository.existsByLogin(datosRegistroUsuario.login())) {
            return ResponseEntity.badRequest().body("Ya existe un Usuario con este login ");
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(datosRegistroUsuario.login());
        usuario.setClave(passwordEncoder.encode(datosRegistroUsuario.clave()));

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario creado correctamente");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable Long id, @Valid @RequestBody DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        if (usuarioRepository.existsByLogin(datosActualizarUsuario.login())) {
            return ResponseEntity.badRequest().body("Ya existe un Usuario con este login");
        }

        usuario.setLogin(datosActualizarUsuario.login());
        usuario.setClave(passwordEncoder.encode(datosActualizarUsuario.clave()));

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok("Usuario eliminado correctamente ");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obtenerDetalleUsuario(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(" se requiere un ID");
        }

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        DatosDetalleUsuario datosDetalleUsuario = new DatosDetalleUsuario(usuario);
        return ResponseEntity.ok(datosDetalleUsuario);
    }
}

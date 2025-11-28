package tdunu.MsCaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tdunu.MsCaja.model.entity.Usuario;
import tdunu.MsCaja.model.request.UsuarioRequest;
import tdunu.MsCaja.model.response.UsuarioResponse;
import tdunu.MsCaja.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Integer id) {
        UsuarioResponse usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.createUsuario(usuarioRequest);
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.updateUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
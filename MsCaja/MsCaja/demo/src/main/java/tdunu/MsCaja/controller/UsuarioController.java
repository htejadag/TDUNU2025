package tdunu.MsCaja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
        @Operation(summary = "Obtiene todos los usuarios")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(mediaType = "*/*",
                    array = @ArraySchema(schema = @Schema(implementation = UsuarioResponse.class)),
                    examples = {
                        @ExampleObject(value = "[{\"idUsuario\":0,\"codigo\":\"string\"}]")
                    }
                )
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<List<UsuarioResponse>> getAllUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.getAllUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
        @Operation(summary = "Obtiene un usuario por su id")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = UsuarioResponse.class),
                    examples = {
                        @ExampleObject(value = "{\"idUsuario\":0,\"codigo\":\"string\"}")
                    }
                )
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Integer id) {
        UsuarioResponse usuario = usuarioService.getUsuarioById(id);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
        @Operation(summary = "Crea un nuevo usuario")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Creado",
                content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = UsuarioResponse.class),
                    examples = {
                        @ExampleObject(value = "{\"idUsuario\":0,\"codigo\":\"string\"}")
                    }
                )
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.createUsuario(usuarioRequest);
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("/{id}")
        @Operation(summary = "Actualiza un usuario existente")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                content = @Content(mediaType = "*/*",
                    schema = @Schema(implementation = UsuarioResponse.class),
                    examples = {
                        @ExampleObject(value = "{\"idUsuario\":0,\"codigo\":\"string\"}")
                    }
                )
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuario = usuarioService.updateUsuario(id, usuarioRequest);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
        @Operation(summary = "Elimina un usuario por id")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
        })
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
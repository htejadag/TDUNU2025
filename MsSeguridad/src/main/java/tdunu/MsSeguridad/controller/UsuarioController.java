package tdunu.MsSeguridad.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;
import tdunu.MsSeguridad.service.UsuarioService;
import tdunu.MsSeguridad.util.ApiRoutes;

import java.util.List;

@RestController
@RequestMapping(ApiRoutes.USUARIO.BASE)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping(ApiRoutes.USUARIO.LISTAR)
    public List<UsuarioResponse> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/activos")
    public List<UsuarioResponse> activos() {
        return usuarioService.listarActivos();
    }

    @GetMapping("/desactivados")
    public List<UsuarioResponse> desactivados() {
        return usuarioService.listarDesactivados();
    }

    @GetMapping(ApiRoutes.USUARIO.OBTENER_POR_ID + "/{id}")
    public UsuarioResponse obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @GetMapping("/codigo/{codUsuario}")
    public UsuarioResponse obtenerPorCodigo(@PathVariable String codUsuario) {
        return usuarioService.obtenerPorCodigo(codUsuario);
    }

    @PostMapping(ApiRoutes.USUARIO.GUARDAR)
    public UsuarioResponse guardar(@RequestBody UsuarioRequest request) {
        return usuarioService.guardar(request);
    }

    @PutMapping("/editar/{codUsuario}")
    public UsuarioResponse editar(@PathVariable String codUsuario,
                                  @RequestBody UsuarioRequest request) {
        return usuarioService.editar(codUsuario, request);
    }

    @DeleteMapping(ApiRoutes.USUARIO.ELIMINAR + "/{codUsuario}")
    public void eliminar(@PathVariable String codUsuario) {
        usuarioService.eliminarPorCodigo(codUsuario);
    }

    @PutMapping("/estado/{codUsuario}")
    public UsuarioResponse cambiarEstado(@PathVariable String codUsuario) {
        return usuarioService.cambiarEstado(codUsuario);
    }
}

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

    // LISTAR TODOS
    @GetMapping(ApiRoutes.USUARIO.LISTAR)
    public List<UsuarioResponse> listar() {
        return usuarioService.listar();
    }

    // LISTAR SOLO ACTIVOS
    @GetMapping(ApiRoutes.USUARIO.LISTAR_ACTIVOS)
    public List<UsuarioResponse> listarActivos() {
        return usuarioService.listarActivos();
    }

    // LISTAR SOLO DESACTIVADOS
    @GetMapping(ApiRoutes.USUARIO.LISTAR_DESACTIVADOS)
    public List<UsuarioResponse> listarDesactivados() {
        return usuarioService.listarDesactivados();
    }

    // BUSCAR POR CODIGO
    @GetMapping(ApiRoutes.USUARIO.OBTENER_POR_CODIGO + "/{codUsuario}")
    public UsuarioResponse obtenerPorCodigo(@PathVariable String codUsuario) {
        return usuarioService.obtenerPorCodigo(codUsuario);
    }

    // GUARDAR
    @PostMapping(ApiRoutes.USUARIO.GUARDAR)
    public UsuarioResponse guardar(@RequestBody UsuarioRequest request) {
        return usuarioService.guardar(request);
    }

    // EDITAR (incluye editar estado)
    @PutMapping(ApiRoutes.USUARIO.EDITAR + "/{codUsuario}")
    public UsuarioResponse editar(@PathVariable String codUsuario,
            @RequestBody UsuarioRequest request) {
        return usuarioService.editar(codUsuario, request);
    }

// ELIMINAR LOGICO POR CODIGO
    @DeleteMapping(ApiRoutes.USUARIO.ELIMINAR + "/{codUsuario}")
    public void eliminar(@PathVariable String codUsuario) {
        usuarioService.eliminarPorCodigo(codUsuario);
    }

    @PostMapping("/{codUsuario}/roles/{idRole}")
    public UsuarioResponse asignarRole(@PathVariable String codUsuario, @PathVariable Long idRole) {
        return usuarioService.asignarRole(codUsuario, idRole);
    }

    @DeleteMapping("/{codUsuario}/roles/{idRole}")
    public UsuarioResponse quitarRole(@PathVariable String codUsuario, @PathVariable Long idRole) {
        return usuarioService.quitarRole(codUsuario, idRole);
    }

}

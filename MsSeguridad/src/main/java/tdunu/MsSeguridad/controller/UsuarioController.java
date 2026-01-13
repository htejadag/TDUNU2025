package tdunu.MsSeguridad.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tdunu.MsSeguridad.model.request.UsuarioRequest;
import tdunu.MsSeguridad.model.response.UsuarioResponse;
import tdunu.MsSeguridad.service.UsuarioService;
import tdunu.MsSeguridad.util.ApiRoutes;

@RestController
@RequestMapping(ApiRoutes.USUARIO.BASE)
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasAuthority('VER_USUARIO')")
    @GetMapping(ApiRoutes.USUARIO.LISTAR)
    public List<UsuarioResponse> listar() {
        return usuarioService.listar();
    }

    @PreAuthorize("hasAuthority('AC_INA_USUARIOS')")
    @GetMapping(ApiRoutes.USUARIO.LISTAR_ACTIVOS)
    public List<UsuarioResponse> listarActivos() {
        return usuarioService.listarActivos();
    }

    @PreAuthorize("hasAuthority('AC_INA_USUARIOS')")
    @GetMapping(ApiRoutes.USUARIO.LISTAR_DESACTIVADOS)
    public List<UsuarioResponse> listarDesactivados() {
        return usuarioService.listarDesactivados();
    }

    @PreAuthorize("hasAuthority('VER_USUARIO')")
    @GetMapping(ApiRoutes.USUARIO.OBTENER_POR_CODIGO + "/{codUsuario}")
    public UsuarioResponse obtenerPorCodigo(@PathVariable String codUsuario) {
        return usuarioService.obtenerPorCodigo(codUsuario);
    }

    @PreAuthorize("hasAuthority('GUARDAR_USUARIO')")

    @PostMapping(ApiRoutes.USUARIO.GUARDAR)
    public UsuarioResponse guardar(@RequestBody UsuarioRequest request) {
        return usuarioService.guardar(request);
    }

    @PreAuthorize("hasAuthority('EDITAR_USUARIO')")
    @PutMapping(ApiRoutes.USUARIO.EDITAR + "/{codUsuario}")
    public UsuarioResponse editar(@PathVariable String codUsuario, @RequestBody UsuarioRequest request) {
        return usuarioService.editar(codUsuario, request);
    }

    @PreAuthorize("hasAuthority('ELIMINAR_USUARIO')")

    @DeleteMapping(ApiRoutes.USUARIO.ELIMINAR + "/{codUsuario}")
    public void eliminar(@PathVariable String codUsuario) {
        usuarioService.eliminarPorCodigo(codUsuario);
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @PostMapping("/{codUsuario}" + ApiRoutes.USUARIO.ROLES + "/{idRole}")
    public UsuarioResponse asignarRole(@PathVariable String codUsuario, @PathVariable Long idRole) {
        return usuarioService.asignarRole(codUsuario, idRole);
    }

    @PreAuthorize("hasAuthority('ASIGNA_ROL_USUARIO')")
    @DeleteMapping("/{codUsuario}" + ApiRoutes.USUARIO.ROLES + "/{idRole}")
    public UsuarioResponse quitarRole(@PathVariable String codUsuario, @PathVariable Long idRole) {
        return usuarioService.quitarRole(codUsuario, idRole);
    }
}

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

    @GetMapping(ApiRoutes.USUARIO.OBTENER_POR_ID + "/{id}")
    public UsuarioResponse obtener(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id);
    }

    @PostMapping(ApiRoutes.USUARIO.GUARDAR)
    public UsuarioResponse guardar(@RequestBody UsuarioRequest request) {
        return usuarioService.guardar(request);
    }

    @DeleteMapping(ApiRoutes.USUARIO.ELIMINAR + "/{codigo}")
    public void eliminar(@PathVariable("codigo") String codUsuario) {
        usuarioService.eliminarPorCodigo(codUsuario);
    }
}

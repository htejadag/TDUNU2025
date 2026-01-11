<<<<<<< HEAD
package tdunu2025.msbiblioteca.service;
=======
package TDUNU2025.Msbiblioteca.service;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.request.DetalleUsuarioRequest;
import tdunu2025.msbiblioteca.model.response.DetalleUsuarioResponse;
=======
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface DetalleUsuarioService {

    List<DetalleUsuarioResponse> listar();

    DetalleUsuarioResponse obtener(Long idUsuario);

    DetalleUsuarioResponse registrar (DetalleUsuarioRequest request);
    
    DetalleUsuarioResponse actualizar (long idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Long idUsuario);
}
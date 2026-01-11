package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import TDUNU2025.Msbiblioteca.model.request.DetalleUsuarioRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleUsuarioResponse;

import java.util.List;

public interface DetalleUsuarioService {
    
    List<DetalleUsuarioResponse> listarTodo();

    // Buscamos por IdUsuario (Negocio)
    DetalleUsuarioResponse obtenerPorIdUsuario(Integer idUsuario);

    // Para uso desde Controller (Recibe DTO)
    DetalleUsuarioResponse guardar(DetalleUsuarioRequest request);

    // Para uso interno (Kafka) - Recibe Entidad
    void guardar(DetalleUsuario entity);
    
    // Vital para la idempotencia de Kafka
    boolean existePorIdUsuario(Integer idUsuario);
    
    DetalleUsuarioResponse actualizar(Integer idUsuario, DetalleUsuarioRequest request);
    
    void eliminar(Integer idUsuario);
}
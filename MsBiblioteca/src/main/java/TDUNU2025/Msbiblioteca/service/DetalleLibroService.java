package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import java.util.List;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar(); // Antes: listarDetallesLibro

    DetalleLibroResponse obtener(Long id); // Antes: obtenerDetalleLibroPorId

    DetalleLibroResponse registrar(DetalleLibroRequest request); // Antes: guardarDetalleLibro

    DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request); // Antes: actualizarDetalleLibro

    void eliminar(Long id); // Antes: eliminarDetalleLibro
}
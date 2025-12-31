package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;
import java.util.List;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listarDetallesLibro();

    DetalleLibroResponse obtenerDetalleLibroPorId(Long id);

    DetalleLibroResponse guardarDetalleLibro(DetalleLibroRequest request);

    DetalleLibroResponse actualizarDetalleLibro(Long id, DetalleLibroRequest request);

    void eliminarDetalleLibro(Long id);
}
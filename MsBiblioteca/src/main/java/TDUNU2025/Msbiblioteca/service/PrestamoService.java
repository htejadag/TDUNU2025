package TDUNU2025.Msbiblioteca.service;

import TDUNU2025.Msbiblioteca.model.request.PrestamoRequest;
import TDUNU2025.Msbiblioteca.model.response.PrestamoResponse;
import java.util.List;

public interface PrestamoService {
    
    List<PrestamoResponse> listarPrestamos();
    
    PrestamoResponse obtenerPrestamoPorId(Integer id);
    
    PrestamoResponse guardarPrestamo(PrestamoRequest request);

    PrestamoResponse registrarDevolucion(Integer id, String observaciones);
    
    void eliminarPrestamo(Integer id);
}
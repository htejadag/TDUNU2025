package TDUNU2025.Msbiblioteca.service;

import java.util.List;
import java.util.Optional;

import TDUNU2025.Msbiblioteca.model.entity.Prestamo;

public interface PrestamoService {
    
    List<Prestamo> listarPrestamos();
    
    Optional<Prestamo> obtenerPrestamoPorId(Integer id);
    
    Prestamo guardarPrestamo(Prestamo prestamo);
    
    void eliminarPrestamo(Integer id);
}
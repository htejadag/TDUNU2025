package com.unu.TDUNU2025.Msbiblioteca.service;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    
    List<Prestamo> listarPrestamos();
    
    Optional<Prestamo> obtenerPrestamoPorId(Integer id);
    
    Prestamo guardarPrestamo(Prestamo prestamo);
    
    void eliminarPrestamo(Integer id);
}
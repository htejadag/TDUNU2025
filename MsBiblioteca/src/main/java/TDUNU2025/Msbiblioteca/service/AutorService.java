package com.unu.TDUNU2025.Msbiblioteca.service;

import java.util.List;
import java.util.Optional;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Autor;

// INTERFAZ: Solo declara los métodos, no la lógica.
public interface AutorService {
    
    List<Autor> listarAutores();
    
    Optional<Autor> obtenerAutorPorId(Integer id);
    
    Autor guardarAutor(Autor autor);
    
    void eliminarAutor(Integer id);
}
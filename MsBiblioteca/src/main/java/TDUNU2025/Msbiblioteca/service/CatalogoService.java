package TDUNU2025.Msbiblioteca.service;

import java.util.List;
import java.util.Optional;

import TDUNU2025.Msbiblioteca.model.entity.Catalogo;

public interface CatalogoService {
    
    // Método para obtener todos los registros de catálogo
    List<Catalogo> listarCatalogos();
    
    // Método para buscar un catálogo específico por su ID
    Optional<Catalogo> obtenerCatalogoPorId(Integer id);
    
    // Método para crear o actualizar un catálogo
    Catalogo guardarCatalogo(Catalogo catalogo);
    
    // Método para eliminar un catálogo por su ID
    void eliminarCatalogo(Integer id);
}
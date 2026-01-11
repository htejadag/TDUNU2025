<<<<<<< HEAD
package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu2025.msbiblioteca.model.entity.LibroCategoria;
=======
package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {
    
    boolean existsByLibro_IdLibroAndCategoriaLibro_IdCategoria (Long idLibro, Long idCategoria);
}

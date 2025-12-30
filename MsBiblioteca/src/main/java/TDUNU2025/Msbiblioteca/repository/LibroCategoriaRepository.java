package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;

public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {

    // Evitar duplicados: un libro no debe tener misma categoría 2 veces
    boolean existsByIdLibroAndIdCategoria(Long idLibro, Long idCategoria);
}

package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdunu2025.msbiblioteca.model.entity.LibroCategoria;

public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {
    
    boolean existsByLibro_IdLibroAndCategoriaLibro_IdCategoria (Long idLibro, Long idCategoria);
}

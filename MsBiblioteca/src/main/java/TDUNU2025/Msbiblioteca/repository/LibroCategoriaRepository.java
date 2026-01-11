package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;

public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {
    
    boolean existsByLibro_IdLibroAndCategoriaLibro_IdCategoria (Long idLibro, Long idCategoria);
}

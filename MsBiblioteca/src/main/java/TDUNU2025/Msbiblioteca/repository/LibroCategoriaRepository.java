package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.LibroCategoria;
@Repository
public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {
    
    boolean existsByLibro_IdLibroAndCategoriaLibro_IdCategoria (Long idLibro, Long idCategoria);
}

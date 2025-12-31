package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.LibroCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroCategoriaRepository extends JpaRepository<LibroCategoria, Long> {

    List<LibroCategoria> findByIdLibro(Long idLibro);

    List<LibroCategoria> findByIdCategoria(Long idCategoria);

    boolean existsByIdLibroAndIdCategoria(Long idLibro, Long idCategoria);

    void deleteByIdLibro(Long idLibro);
}
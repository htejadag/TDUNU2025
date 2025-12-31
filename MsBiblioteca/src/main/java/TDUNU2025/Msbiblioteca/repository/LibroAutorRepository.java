package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {

    List<LibroAutor> findByIdLibro(Long idLibro);

    List<LibroAutor> findByIdAutor(Integer idAutor);

    boolean existsByIdLibroAndIdAutor(Long idLibro, Integer idAutor);
    
    void deleteByIdLibro(Long idLibro);
}
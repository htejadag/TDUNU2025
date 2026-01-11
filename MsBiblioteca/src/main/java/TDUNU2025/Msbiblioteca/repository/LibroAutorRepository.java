package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;

public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {

    boolean existsByLibro_IdLibroAndAutor_IdAutorAndRol(Long idLibro,Long idAutor, String rol );

}

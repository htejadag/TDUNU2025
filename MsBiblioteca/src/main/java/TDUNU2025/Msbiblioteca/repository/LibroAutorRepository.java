package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;

public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {

    // Verifica si ya existe un autor registrado para un libro con ese rol
    boolean existsByIdLibroAndIdAutorAndRol(Long idLibro, Long idAutor, String rol);

}

<<<<<<< HEAD
package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu2025.msbiblioteca.model.entity.LibroAutor;
=======
package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.LibroAutor;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

public interface LibroAutorRepository extends JpaRepository<LibroAutor, Long> {

    boolean existsByLibro_IdLibroAndAutor_IdAutorAndRol(Long idLibro,Long idAutor, String rol );

}

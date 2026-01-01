package TDUNU2025.Msbiblioteca.repository; // O el paquete que uses para tus repositorios

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.entity.Libro;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {
        //busca el objeto libro
        boolean existsByLibro (Libro libro);
        //busca navegando el id
        boolean existsByLibro_IdLibro(Long idLibro);
}

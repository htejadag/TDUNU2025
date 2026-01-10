package tdunu2025.msbiblioteca.repository; // O el paquete que uses para tus repositorios

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.DetalleLibro;
import tdunu2025.msbiblioteca.model.entity.Libro;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {
        //busca el objeto libro
        boolean existsByLibro (Libro libro);
        //busca navegando el id
        boolean existsByLibro_IdLibro(Long idLibro);
}

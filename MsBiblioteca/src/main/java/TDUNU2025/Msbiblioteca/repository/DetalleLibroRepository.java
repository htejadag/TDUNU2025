package tdunu2025.msbiblioteca.repository; // O el paquete que uses para tus repositorios

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.DetalleLibro;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {
        
        Optional<DetalleLibro>findByLibro_IdLibro(Long idLibro);        
}

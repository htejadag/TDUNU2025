package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;

public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {

    // Opcional pero útil: verificar si ya existe un detalle para un libro
    boolean existsByIdLibro(Long idLibro);
}

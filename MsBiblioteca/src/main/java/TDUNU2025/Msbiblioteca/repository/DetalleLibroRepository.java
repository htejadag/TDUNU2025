package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;

public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {

    boolean existsByIdLibro(Long idLibro);
}

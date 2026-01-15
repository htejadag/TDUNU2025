package tdunu2025.msbiblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.Prestamo;


@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByIdUsuario(Long idUsuario);
    List<Prestamo> findByFechaDevolucionIsNull();
    List<Prestamo> findByIdUsuarioAndFechaDevolucionIsNull(Long idUsuario);
}
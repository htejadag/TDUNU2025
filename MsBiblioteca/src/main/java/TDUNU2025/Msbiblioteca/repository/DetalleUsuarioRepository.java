package tdunu2025.msbiblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.DetalleUsuario;


@Repository
public interface DetalleUsuarioRepository extends JpaRepository<DetalleUsuario, Long> {

    Optional<DetalleUsuario> findByIdUsuario(Long idUsuario);
    boolean existsByIdUsuario(Long idUsuario);
}
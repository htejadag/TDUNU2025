package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetalleUsuarioRepository extends JpaRepository<DetalleUsuario, Integer> {
    
    Optional<DetalleUsuario> findByIdUsuario(Integer idUsuario);
}
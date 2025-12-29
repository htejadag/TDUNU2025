package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;

@Repository
public interface DetalleUsuarioRepository extends JpaRepository<DetalleUsuario, Integer> {
}
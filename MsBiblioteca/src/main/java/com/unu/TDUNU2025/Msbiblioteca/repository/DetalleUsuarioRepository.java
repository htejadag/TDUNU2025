package com.unu.TDUNU2025.Msbiblioteca.repository;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleUsuarioRepository extends JpaRepository<DetalleUsuario, Integer> {
}
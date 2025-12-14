package com.unu.TDUNU2025.Msbiblioteca.repository; // O el paquete que uses para tus repositorios

import com.unu.TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Integer> {

}
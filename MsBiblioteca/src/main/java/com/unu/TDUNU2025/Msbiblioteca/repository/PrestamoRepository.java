package com.unu.TDUNU2025.Msbiblioteca.repository;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    // Heredamos todos los métodos CRUD básicos de JpaRepository:
    // save(), findById(), findAll(), deleteById(), etc.
}
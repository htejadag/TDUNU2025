package com.unu.TDUNU2025.Msbiblioteca.repository;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {
    // JpaRepository ya incluye los métodos: save(), findById(), findAll(), deleteById(), etc.
}
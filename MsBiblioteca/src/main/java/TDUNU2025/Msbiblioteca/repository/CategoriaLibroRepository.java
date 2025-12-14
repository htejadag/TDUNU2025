package com.unu.TDUNU2025.Msbiblioteca.repository;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.CategoriaLibro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaLibroRepository extends JpaRepository<CategoriaLibro, Long> {
}
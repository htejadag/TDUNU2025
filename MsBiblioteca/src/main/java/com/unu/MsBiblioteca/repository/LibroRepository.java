package com.unu.MsBiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unu.MsBiblioteca.model.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
}

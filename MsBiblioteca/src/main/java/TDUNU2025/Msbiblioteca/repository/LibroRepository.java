package tdunu2025.msbiblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.Libro;

@Repository

public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
    Optional<Libro> findByIsbn(String isbn);
}

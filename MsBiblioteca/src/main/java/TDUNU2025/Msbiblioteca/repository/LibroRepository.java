package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu2025.msbiblioteca.model.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
}

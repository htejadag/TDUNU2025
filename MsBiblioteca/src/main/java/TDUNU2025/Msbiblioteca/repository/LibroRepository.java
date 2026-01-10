package tdunu2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu2025.Msbiblioteca.model.entity.Libro;


public interface LibroRepository extends JpaRepository<Libro, Long> {
    boolean existsByIsbn(String isbn);
}

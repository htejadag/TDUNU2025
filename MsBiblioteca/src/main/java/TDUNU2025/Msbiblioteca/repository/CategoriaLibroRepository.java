    package tdunu2025.msbiblioteca.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.CategoriaLibro;

    @Repository
    public interface CategoriaLibroRepository extends JpaRepository<CategoriaLibro, Long> {
    }
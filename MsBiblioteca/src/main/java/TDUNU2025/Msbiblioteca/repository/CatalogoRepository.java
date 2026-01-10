package tdunu2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.Msbiblioteca.model.entity.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
    // JpaRepository ya incluye los métodos: save(), findById(), findAll(), deleteById(), etc.
}
package tdunu2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu2025.Msbiblioteca.model.entity.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial,Long>{
        boolean existsByNombre(String nombre);
}

package tdunu2025.msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.msbiblioteca.model.entity.Editorial;
@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long>{
        boolean existsByNombre(String nombre);
}

package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository 
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    
    boolean existsByIdPersona(Integer idPersona);
}
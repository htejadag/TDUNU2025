package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
    // Al extender JpaRepository, ya tenemos listos los métodos:
    // save(), findById(), findAll(), deleteById(), etc.
}
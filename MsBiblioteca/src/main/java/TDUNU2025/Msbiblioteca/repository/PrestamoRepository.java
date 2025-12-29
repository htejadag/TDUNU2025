package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
    // Heredamos todos los métodos CRUD básicos de JpaRepository:
    // save(), findById(), findAll(), deleteById(), etc.
}
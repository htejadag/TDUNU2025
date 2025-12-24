package TDUNU2025.Msbiblioteca.repository; // O el paquete que uses para tus repositorios

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Integer> {

}
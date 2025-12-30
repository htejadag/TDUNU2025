<<<<<<< HEAD
package TDUNU2025.Msbiblioteca.repository; // O el paquete que uses para tus repositorios

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Integer> {

}
=======
package TDUNU2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;

public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {

    // Opcional pero útil: verificar si ya existe un detalle para un libro
    boolean existsByIdLibro(Long idLibro);
}
>>>>>>> origin/origin/jordinTrujillo

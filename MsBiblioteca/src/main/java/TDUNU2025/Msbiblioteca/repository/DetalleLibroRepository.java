<<<<<<< HEAD
package tdunu2025.msbiblioteca.repository; // O el paquete que uses para tus repositorios
=======
package TDUNU2025.Msbiblioteca.repository; // O el paquete que uses para tus repositorios
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.entity.DetalleLibro;
import tdunu2025.msbiblioteca.model.entity.Libro;
=======
import TDUNU2025.Msbiblioteca.model.entity.DetalleLibro;
import TDUNU2025.Msbiblioteca.model.entity.Libro;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

@Repository
public interface DetalleLibroRepository extends JpaRepository<DetalleLibro, Long> {
        //busca el objeto libro
        boolean existsByLibro (Libro libro);
        //busca navegando el id
        boolean existsByLibro_IdLibro(Long idLibro);
}

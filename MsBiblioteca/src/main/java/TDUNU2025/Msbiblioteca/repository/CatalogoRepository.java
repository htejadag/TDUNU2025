<<<<<<< HEAD
package tdunu2025.msbiblioteca.repository;
=======
package TDUNU2025.Msbiblioteca.repository;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.entity.Catalogo;
=======
import TDUNU2025.Msbiblioteca.model.entity.Catalogo;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
    // JpaRepository ya incluye los métodos: save(), findById(), findAll(), deleteById(), etc.
}
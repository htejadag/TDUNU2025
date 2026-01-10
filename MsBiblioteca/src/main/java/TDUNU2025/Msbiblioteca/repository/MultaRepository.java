<<<<<<< HEAD
package tdunu2025.msbiblioteca.repository;
=======
package TDUNU2025.Msbiblioteca.repository;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import TDUNU2025.Msbiblioteca.model.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import tdunu2025.msbiblioteca.model.entity.Multa;
=======
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByIdUsuario(Long idUsuario);

    List<Multa> findByIdPrestamo(Long idPrestamo);
}
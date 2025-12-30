package TDUNU2025.Msbiblioteca.repository;

<<<<<<< HEAD
=======
import TDUNU2025.Msbiblioteca.model.entity.Multa;
>>>>>>> origin/origin/jordinTrujillo
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TDUNU2025.Msbiblioteca.model.entity.Multa;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {

    // Método para listar todas las multas de un usuario específico
    List<Multa> findByIdUsuario(Integer idUsuario);

    // Método para buscar multas asociadas a un préstamo específico
    List<Multa> findByIdPrestamo(Integer idPrestamo);

}
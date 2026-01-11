package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByIdUsuario(Long idUsuario);

    List<Multa> findByIdPrestamo(Long idPrestamo);
}
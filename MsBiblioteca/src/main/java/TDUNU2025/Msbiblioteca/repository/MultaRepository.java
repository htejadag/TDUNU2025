package tdunu2025.msbiblioteca.repository;

import tdunu2025.msbiblioteca.model.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByIdUsuario(Long idUsuario);

    List<Multa> findByPrestamo_IdPrestamo (Long idPrestamo);
}
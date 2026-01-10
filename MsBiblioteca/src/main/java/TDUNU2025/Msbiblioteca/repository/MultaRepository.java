package tdunu2025.Msbiblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu2025.Msbiblioteca.model.entity.Multa;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    List<Multa> findByIdUsuario(Long idUsuario);

    List<Multa> findByIdPrestamo(Long idPrestamo);
}
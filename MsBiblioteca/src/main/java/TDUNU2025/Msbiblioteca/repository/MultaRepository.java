package TDUNU2025.Msbiblioteca.repository;

import TDUNU2025.Msbiblioteca.model.entity.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {
    
    List<Multa> findByIdUsuario(Integer idUsuario);
    
    boolean existsByIdUsuarioAndIdEstadoMulta(Integer idUsuario, Integer idEstadoMulta);
}
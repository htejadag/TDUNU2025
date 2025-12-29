package Ms_Reingresante.Ms_Reingresante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ms_Reingresante.Ms_Reingresante.model.entity.FichaNoAdeudoModel;


@Repository
public interface FichaNoAdeudoRepository extends JpaRepository<FichaNoAdeudoModel, Integer> {
    
    // Aquí podrías añadir métodos personalizados si los necesitas, por ejemplo:
    // List<FichaNoAdeudoEntity> findByIdProceso(Integer idProceso);
    
}
package Ms_Reingresante.Ms_Reingresante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ms_Reingresante.Ms_Reingresante.model.entity.FichaNoAdeudoEntity;


@Repository
public interface FichaNoAdeudoRepository extends JpaRepository<FichaNoAdeudoEntity, Long> {
    
    // Aquí podrías añadir métodos personalizados si los necesitas, por ejemplo:
    // List<FichaNoAdeudoEntity> findByIdProceso(Integer idProceso);
    
}
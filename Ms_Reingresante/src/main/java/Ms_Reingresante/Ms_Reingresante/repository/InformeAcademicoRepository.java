package Ms_Reingresante.Ms_Reingresante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import Ms_Reingresante.Ms_Reingresante.model.entity.InformeAcademicoModel;


@Repository
public interface InformeAcademicoRepository extends JpaRepository<InformeAcademicoModel, Integer> {

    // Aquí podrías añadir métodos personalizados si los necesitas, por ejemplo:
    // InformeAcademicoEntity findByIdProcesoAndFechaEmisionIsNotNull(Integer idProceso);

}
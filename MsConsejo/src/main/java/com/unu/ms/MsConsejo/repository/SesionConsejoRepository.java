package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface SesionConsejoRepository extends JpaRepository<SesionConsejoModel, Integer> {

    @Query(

            value = "SELECT "
                    + "id_session AS idSession, "
                    + "id_consejo AS idConsejo, "
                    + "numero_sesion AS numeroSesion, "
                    + "fecha_sesion AS fechaSesion, "
                    + "id_tipo_sesion AS idTipoSesion, "
                    + "descripcion AS descripcion, "
                    + "id_estado AS idEstado "
                    + "FROM sesion_consejo "
                    + "WHERE id_consejo = :idConsejo", 
            nativeQuery = true

    )
    List<Object[]> listarSesiones(@Param("idConsejo") int idConsejo);

}

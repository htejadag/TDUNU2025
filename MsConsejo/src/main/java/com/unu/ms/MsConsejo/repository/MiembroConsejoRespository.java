package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MiembroConsejoRespository extends JpaRepository<MiembroConsejoModel, Integer> {

    @Query(

            value = "SELECT "
                    + "mc.id_miembro, "
                    + "mc.id_persona, "
                    + "p.nombres, "
                    + "p.apellidos, "
                    + "mc.id_cargo, "
                    + "mc.fecha_inicio, "
                    + "mc.fecha_fin "
                    + "FROM miembro_consejo mc "
                    + "JOIN persona p ON mc.id_persona = p.id_persona "
                    + "WHERE mc.id_consejo = :idConsejo", 
            nativeQuery = true

    )
    List<Object[]> listarMiembrosDeUnConsejo(@Param("idConsejo") int idConsejo);

}

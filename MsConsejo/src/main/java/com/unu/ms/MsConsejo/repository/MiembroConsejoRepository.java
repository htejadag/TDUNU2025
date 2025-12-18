package com.unu.ms.MsConsejo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface MiembroConsejoRepository extends JpaRepository<MiembroConsejoModel, Integer> {

    public List<MiembroConsejoModel> findByConsejo_IdConsejo(Integer idConsejo);
    public List<MiembroConsejoModel> findByIdPersona(Integer idPersona);
    public List<MiembroConsejoModel> findByIdCargo(Integer idCargo);
    public List<MiembroConsejoModel> findByConsejo_IdConsejoAndIdPersona(Integer idConsejo, Integer idPersona);

    // Miembros activos por cargo (fechaFin es null = activo)
    @Query("SELECT m FROM MiembroConsejoModel m WHERE m.idCargo = :idCargo AND m.fechaFin IS NULL")
    List<MiembroConsejoModel> findActivosByIdCargo(@Param("idCargo") Integer idCargo);
    
    // Miembros activos por consejo (fechaFin es null = activo)
    @Query("SELECT m FROM MiembroConsejoModel m WHERE m.consejo.idConsejo = :idConsejo AND m.fechaFin IS NULL")
    List<MiembroConsejoModel> findActivosByConsejoId(@Param("idConsejo") Integer idConsejo);

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

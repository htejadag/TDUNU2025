package com.unu.ms.MsConsejo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AsistenciaSesionRepository extends JpaRepository<AsistenciaSesionModel, Integer> {

    public List<AsistenciaSesionModel> findBySesion_IdSesion(Integer idSesion);
    public List<AsistenciaSesionModel> findByMiembro_IdMiembro(Integer idMiembro);
    public List<AsistenciaSesionModel> findByIdEstadoAsistencia(Integer idEstadoAsistencia);
    public List<AsistenciaSesionModel> findBySesion_IdSesionAndMiembro_IdMiembro(Integer idSesion, Integer idMiembro);
    
    // Nuevo método para contar por estado de asistencia en una sesión
    long countBySesion_IdSesionAndIdEstadoAsistencia(Integer idSesion, Integer idEstadoAsistencia);
    
    long countBySesion_IdSesion(Integer idSesion);

    @Query(

            value = "SELECT "
                    + "a.id_asistencia, "
                    + "a.id_miembro, "
                    + "p.nombres, "
                    + "p.apellidos, "
                    + "a.id_estado_asistencia "
                    + "FROM asistencia_sesion a "
                    + "JOIN miembro_consejo mc ON a.id_miembro = mc.id_miembro "
                    + "JOIN persona p ON mc.id_persona = p.id_persona "
                    + "WHERE a.id_sesion = :idSesion", 
            nativeQuery = true

    )
    List<Object[]> listarAsistenciaDeUnaSesion(@Param("idSesion") int idSesion);

}

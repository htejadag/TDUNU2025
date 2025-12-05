package com.unu.ms.MsConsejo.repository;

import com.unu.ms.MsConsejo.model.entity.AsistenciaConsejero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaConsejeroRepository extends JpaRepository<AsistenciaConsejero, Integer> {
    
    List<AsistenciaConsejero> findBySesionConsejo_IdSesion(Integer idSesion);
    
    List<AsistenciaConsejero> findByIdMiembro(Integer idMiembro);
    
    List<AsistenciaConsejero> findByIdEstadoAsistencia(Integer idEstadoAsistencia);
    
    List<AsistenciaConsejero> findBySesionConsejo_IdSesionAndIdMiembro(Integer idSesion, Integer idMiembro);
}

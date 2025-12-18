package com.unu.ms.MsSecretariaAcademica.repository;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;

@Repository
public interface SolicitudRepository extends JpaRepository<SolicitudModel, Integer> {


    public List<SolicitudModel> findByIdPersona(Integer idPersona);
    public List<SolicitudModel> findByIdEstado(Integer idEstado);
    public List<SolicitudModel> findByIdPersonaAndIdEstado(Integer idPersona, Integer idEstado);
    public List<SolicitudModel> findByIdTipoSolicitud(Integer idTipoSolicitud);
    public List<SolicitudModel> findByIdTipoSolicitudAndIdEstado(Integer idTipoSolicitud, Integer idEstado);
    public List<SolicitudModel> findByFechaSolicitudBetween(String fechaInicio, String fechaFin);
}

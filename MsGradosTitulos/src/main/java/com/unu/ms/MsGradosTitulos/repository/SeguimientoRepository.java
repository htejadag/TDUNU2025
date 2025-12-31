package com.unu.ms.MsGradosTitulos.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsGradosTitulos.model.entity.SeguimientoModel;

@Repository
public interface SeguimientoRepository extends JpaRepository<SeguimientoModel, Integer> {

    public List<SeguimientoModel> findByIdEntidadCatalogoAndEntidadId(Integer idEntidadCatalogo, Integer entidadId);
    public List<SeguimientoModel> findByIdEstado(Integer idEstado);
    public List<SeguimientoModel> findByIdUsuario(Integer idUsuario);
    public List<SeguimientoModel> findByIdEntidadCatalogoAndEntidadIdOrderByFechaRegistroDesc( Integer idEntidadCatalogo, Integer entidadId);

}

package com.unu.ms.MsSecretariaAcademica.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.unu.ms.MsSecretariaAcademica.model.entity.Seguimiento;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {

    public List<Seguimiento> findByEntidadCatalogoIdAndEntidadId(Integer entidadCatalogoId, Integer entidadId);
    public List<Seguimiento> findByIdEstado(Integer idEstado);
    public List<Seguimiento> findByIdUsuario(Integer idUsuario);
    public List<Seguimiento> findByEntidadCatalogoIdAndEntidadIdOrderByFechaRegistroDesc(Integer entidadCatalogoId, Integer entidadId);

}

package com.unu.ms.MsGradosTitulos.repository;

import com.unu.ms.MsGradosTitulos.model.entity.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, Integer> {
    
    List<Seguimiento> findByIdEntidadCatalogoAndEntidadId(Integer idEntidadCatalogo, Integer entidadId);
    
    List<Seguimiento> findByIdEstado(Integer idEstado);
    
    List<Seguimiento> findByIdUsuario(Integer idUsuario);
    
    List<Seguimiento> findByIdEntidadCatalogoAndEntidadIdOrderByFechaRegistroDesc(
        Integer idEntidadCatalogo, 
        Integer entidadId
    );
}

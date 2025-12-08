package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pago.model.entity.AperturaCierreCajaModel;

@Repository("ape_cie_cajaRepositorio")
public interface AperturaCierreCajaRepository extends JpaRepository<AperturaCierreCajaModel, Serializable> {

    @Modifying
    @Transactional
    @Query("UPDATE AperturaCierreCajaModel a SET a.activo = false  WHERE a.apertura_cierre_cajaid = :id")
    public void desactivar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE AperturaCierreCajaModel a SET a.es_eliminado = true WHERE a.apertura_cierre_cajaid = :id")
    public void eliminar(@Param("id") int id);

}

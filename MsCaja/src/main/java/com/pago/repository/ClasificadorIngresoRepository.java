package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pago.model.entity.ClasificadorIngresoModel;

@Repository("clasificador_ingresoRepositorio")
public interface ClasificadorIngresoRepository extends JpaRepository<ClasificadorIngresoModel, Serializable> {
    @Modifying
    @Transactional
    @Query("UPDATE ClasificadorIngresoModel a SET a.activo = false WHERE a.clasificador_ingresoid = :id")
    public void desactivar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE ClasificadorIngresoModel a SET a.es_eliminado = true WHERE a.clasificador_ingresoid = :id")
    public void eliminar(@Param("id") int id);
}

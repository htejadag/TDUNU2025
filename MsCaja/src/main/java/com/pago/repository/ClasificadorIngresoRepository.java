package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pago.model.entity.ClasificadorIngresoModel;

@Repository("clasificadorIngresoRepositorio")
public interface ClasificadorIngresoRepository extends JpaRepository<ClasificadorIngresoModel, Serializable> {
    @Modifying
    @Transactional
    @Query("UPDATE ClasificadorIngresoModel a SET a.activo = false WHERE a.clasificadorIngresoId = :id")
    public void desactivar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE ClasificadorIngresoModel a SET a.esEliminado = true WHERE a.clasificadorIngresoId = :id")
    public void eliminar(@Param("id") int id);
}

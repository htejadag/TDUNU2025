package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pago.model.entity.NotaCreditoModel;

@Repository("notaCreditoRepositorio")
public interface NotaCreditoRepository extends JpaRepository<NotaCreditoModel, Serializable> {

    @Modifying
    @Transactional
    @Query("UPDATE NotaCreditoModel a SET a.activo = false WHERE a.notaCreditoId = :id")
    public void desactivar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE NotaCreditoModel a SET a.esEliminado = true WHERE a.notaCreditoId = :id")
    public void eliminar(@Param("id") int id);

}

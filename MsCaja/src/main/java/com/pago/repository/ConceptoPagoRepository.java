package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.pago.model.entity.ConceptoPagoModel;

@Repository("concepto_pagoRepositorio")
public interface ConceptoPagoRepository extends JpaRepository<ConceptoPagoModel, Serializable> {
    @Modifying
    @Transactional
    @Query("UPDATE ConceptoPagoModel a SET a.activo = false WHERE a.concepto_pagoid = :id")
    public void desactivar(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("UPDATE ConceptoPagoModel a SET a.es_eliminado = true WHERE a.concepto_pagoid = :id")
    public void eliminar(@Param("id") int id);
}

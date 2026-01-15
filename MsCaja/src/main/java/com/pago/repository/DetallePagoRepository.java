package com.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pago.model.entity.DetallePagoModel;


@Repository
public interface DetallePagoRepository extends JpaRepository<DetallePagoModel, Integer> {
  
}


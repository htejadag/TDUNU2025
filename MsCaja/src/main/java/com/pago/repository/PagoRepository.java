package com.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pago.model.entity.PagoModel;


@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Integer> {
  
}

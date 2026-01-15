package com.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pago.model.entity.NotaCreditoModel;

@Repository
public interface NotaCreditoRepository extends JpaRepository<NotaCreditoModel, Integer> {

}


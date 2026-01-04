package com.pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pago.model.entity.DevolucionModel;

@Repository
public interface DevolucionRepository extends JpaRepository<DevolucionModel, Integer> {

}

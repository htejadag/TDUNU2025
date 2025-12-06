package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.entity.PagoModel;


@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Integer> {
  
}

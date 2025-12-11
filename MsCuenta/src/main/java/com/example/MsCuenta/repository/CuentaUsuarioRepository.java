package com.example.MsCuenta.repository;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuarioModel, Integer> {
     
    
}
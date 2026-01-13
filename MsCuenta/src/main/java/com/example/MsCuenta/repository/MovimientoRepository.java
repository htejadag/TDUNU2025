package com.example.MsCuenta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MsCuenta.model.entity.MovimientoModel;

public interface MovimientoRepository extends JpaRepository<MovimientoModel, Integer> {

    List<MovimientoModel> findByIdCuentaUsuarioId(Integer idCuentaUsuario);

    
}

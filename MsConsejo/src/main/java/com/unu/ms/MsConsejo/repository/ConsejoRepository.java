package com.unu.ms.MsConsejo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ConsejoRepository extends JpaRepository<ConsejoModel, Integer> {
    
    public Optional<ConsejoModel> findByNombre(String nombre);
    public List<ConsejoModel> findByIdEstado(Integer idEstado);

}

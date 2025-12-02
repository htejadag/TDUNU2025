package com.example.MsGeneral.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsGeneral.Model.Entidad.UnidadOrganica;

@Repository
public interface UnidadOrganicaRepositorio extends JpaRepository<UnidadOrganica,Integer> {

    
} 
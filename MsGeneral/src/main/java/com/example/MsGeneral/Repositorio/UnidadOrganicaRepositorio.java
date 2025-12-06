package com.example.MsGeneral.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsGeneral.Entidad.UnidadOrganica;

@Repository
public interface UnidadOrganicaRepositorio extends JpaRepository<UnidadOrganica,Integer> {

    
} 
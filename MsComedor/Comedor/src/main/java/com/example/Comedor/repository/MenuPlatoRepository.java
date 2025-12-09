package com.example.Comedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Comedor.model.entity.MenuPlatoModel;

public interface MenuPlatoRepository extends JpaRepository<MenuPlatoModel,Integer>{
    
}

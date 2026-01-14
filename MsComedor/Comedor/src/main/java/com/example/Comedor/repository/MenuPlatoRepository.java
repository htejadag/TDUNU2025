package com.example.Comedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.example.Comedor.model.entity.MenuPlatoModel;

public interface MenuPlatoRepository extends JpaRepository<MenuPlatoModel,Integer>{

    List<MenuPlatoModel> findByIdMenuDia_MenuSemana_Id(Integer idMenuSemana);


    
}

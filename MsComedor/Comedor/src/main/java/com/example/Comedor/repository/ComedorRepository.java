package com.example.Comedor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Comedor.model.entity.ComedorModel;



@Repository
public interface ComedorRepository extends JpaRepository<ComedorModel,Integer>{

   

    
}

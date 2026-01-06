package com.example.MsPlanEstudios.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.MsPlanEstudios.model.entity.CatalogoModel;

@Repository
public interface CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {

    Optional<CatalogoModel> findByIdAndEstadoTrue(Integer id);
}

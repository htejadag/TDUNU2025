package com.example.mscursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mscursos.model.entity.CatalogoModel;

public interface CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {

    List<CatalogoModel> findByCategoria(String categoria);

    List<CatalogoModel> findByCategoriaAndEstado(String categoria, Boolean estado);

    List<CatalogoModel> findByIdPadre(String idPadre);

    List<CatalogoModel> findByEstado(Boolean estado);
}

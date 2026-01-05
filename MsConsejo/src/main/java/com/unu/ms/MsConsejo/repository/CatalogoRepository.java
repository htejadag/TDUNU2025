package com.unu.ms.MsConsejo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.Catalogo;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

    public Optional<Catalogo> findByCategoriaAndValor(String categoria, String valor);
    public List<Catalogo> findByCategoria(String categoria);

}
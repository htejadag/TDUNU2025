package com.unu.ms.MsConsejo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.CatalogoModel;

@Repository
public interface CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {
    
    public List<CatalogoModel> findByDescripcionCatalogo(String descripcionCatalogo);
    public List<CatalogoModel> findByAbreviaturaCatalogo(String abreviaturaCatalogo);
    public List<CatalogoModel> findByEstadoCatalogo(String estadoCatalogo);
    public List<CatalogoModel> findByPadre_IdCatalogo(Integer idPadre);
    public List<CatalogoModel> findByPadreIsNull();
    
    @Query("SELECT c FROM CatalogoModel c WHERE c.padre.idCatalogo = :idPadre AND c.estadoCatalogo = :estado")
    public List<CatalogoModel> findByPadreAndEstado(@Param("idPadre") Integer idPadre, @Param("estado") String estado);

}

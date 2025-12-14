package com.MsExamen.repository;

import com.MsExamen.model.CatalogoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoDetalleRepository extends JpaRepository<CatalogoDetalle, Integer> {
}

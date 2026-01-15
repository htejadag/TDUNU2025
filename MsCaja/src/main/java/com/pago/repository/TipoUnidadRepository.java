package com.pago.repository;

import com.pago.model.entity.TipoUnidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUnidadRepository extends JpaRepository<TipoUnidad, Integer> {
}


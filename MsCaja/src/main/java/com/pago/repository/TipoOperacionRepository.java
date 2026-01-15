package com.pago.repository;

import com.pago.model.entity.TipoOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoOperacionRepository extends JpaRepository<TipoOperacion, Integer> {
}


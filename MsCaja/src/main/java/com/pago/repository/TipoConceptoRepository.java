package com.pago.repository;

import com.pago.model.entity.TipoConcepto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoConceptoRepository extends JpaRepository<TipoConcepto, Integer> {
}


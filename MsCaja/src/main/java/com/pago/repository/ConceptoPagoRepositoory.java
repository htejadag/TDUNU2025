package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pago.model.entity.ConceptoPagoModel;

public interface ConceptoPagoRepositoory extends JpaRepository<ConceptoPagoModel, Serializable> {

}

package com.pago.repository;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pago.model.entity.ClasificadorIngresoModel;

public interface ClasificadorIngresoRepository extends JpaRepository<ClasificadorIngresoModel, Serializable> {

}

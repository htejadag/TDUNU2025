package com.MsExamen.repository;

import com.MsExamen.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Integer> {
    List<Respuesta> findByPregunta_IdPregunta(Integer idPregunta);
}

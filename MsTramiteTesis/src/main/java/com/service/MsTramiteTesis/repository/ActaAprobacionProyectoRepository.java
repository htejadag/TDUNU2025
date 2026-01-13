package com.service.MsTramiteTesis.repository;

import com.service.MsTramiteTesis.model.entity.ActaAprobacionProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActaAprobacionProyectoRepository extends JpaRepository<ActaAprobacionProyecto, Long> {

    Optional<ActaAprobacionProyecto> findByIdProyecto(Long idProyecto);

    Optional<ActaAprobacionProyecto> findByCodigoActa(String codigoActa);

}

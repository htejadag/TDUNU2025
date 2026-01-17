package com.example.MsPlanEstudios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosPrerequisitoModel;

public interface PlanEstudiosPrerequisitoRepository extends JpaRepository<PlanEstudiosPrerequisitoModel, Integer> {

    List<PlanEstudiosPrerequisitoModel>
        findByIdPlanEstudioDetalleAndEstadoTrue(Integer idPlanEstudioDetalle);
}

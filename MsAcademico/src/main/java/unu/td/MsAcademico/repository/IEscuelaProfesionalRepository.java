package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.entity.FacultadModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEscuelaProfesionalRepository extends JpaRepository<EscuelaProfesionalModel, Integer> {

    public List<EscuelaProfesionalModel> findByEstadoTrue();

    public Optional<EscuelaProfesionalModel> findByIdAndEstadoTrue(Integer id);

    public EscuelaProfesionalModel findByNombre(String nombre);
}
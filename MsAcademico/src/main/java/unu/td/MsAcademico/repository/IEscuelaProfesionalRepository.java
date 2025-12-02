package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;

@Repository
public interface IEscuelaProfesionalRepository extends JpaRepository<EscuelaProfesionalModel, Integer> {

    public EscuelaProfesionalModel findByNombre(String nombre);
}
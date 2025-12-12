package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.FacultadModel;

@Repository
public interface IFacultadRepository extends JpaRepository<FacultadModel, Integer> {

    public FacultadModel findByNombre(String nombre);
}
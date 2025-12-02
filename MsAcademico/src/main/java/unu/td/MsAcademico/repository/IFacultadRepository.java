package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.FacultadModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFacultadRepository extends JpaRepository<FacultadModel, Integer> {

    public List<FacultadModel> findByEstadoTrue();

    public Optional<FacultadModel> findByIdAndEstadoTrue(Integer id);

    public FacultadModel findByNombre(String nombre);
}
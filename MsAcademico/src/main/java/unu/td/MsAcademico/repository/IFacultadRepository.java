package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import unu.td.MsAcademico.model.entity.FacultadModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFacultadRepository extends JpaRepository<FacultadModel, Integer> {

    public List<FacultadModel> findByEliminadoFalse();

    public Optional<FacultadModel> findByIdAndEliminadoFalse(Integer id);

    public FacultadModel findByNombre(String nombre);

    @Modifying
    @Query(value = "UPDATE public.facultades SET eliminado = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void delete(Integer id, String usuarioModificacion);
}
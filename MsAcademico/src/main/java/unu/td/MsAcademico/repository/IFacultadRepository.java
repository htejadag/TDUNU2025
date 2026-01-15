package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.FacultadModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFacultadRepository extends JpaRepository<FacultadModel, Integer> {

    public List<FacultadModel> findByEliminadoFalse();

    public Optional<FacultadModel> findByIdAndEliminadoFalse(Integer id);

    public FacultadModel findByNombre(String nombre);

    @Modifying
    @Query(value = "UPDATE public.facultades SET eliminado = TRUE WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id);

    @Modifying
    @Query(value = "UPDATE public.facultades SET activo = TRUE WHERE id = ?1", nativeQuery = true)
    public void activate(Integer id);

    @Modifying
    @Query(value = "UPDATE public.facultades SET activo = FALSE WHERE id = ?1", nativeQuery = true)
    public void deactivate(Integer id);
}
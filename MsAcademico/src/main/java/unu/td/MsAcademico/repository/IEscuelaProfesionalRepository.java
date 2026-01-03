package unu.td.msacademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.msacademico.model.entity.EscuelaProfesionalModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEscuelaProfesionalRepository extends JpaRepository<EscuelaProfesionalModel, Integer> {

    public List<EscuelaProfesionalModel> findByEliminadoFalse();

    public Optional<EscuelaProfesionalModel> findByIdAndEliminadoFalse(Integer id);

    public EscuelaProfesionalModel findByNombre(String nombre);

    @Query(value = "SELECT * FROM public.\"escuelasProfesionales\" WHERE eliminado = FALSE AND \"idFacultad\" = ?1", nativeQuery = true)
    public List<EscuelaProfesionalModel> findByIdFacultadIdAndEliminadoFalse(Integer idFacultad);

    @Modifying
    @Query(value = "UPDATE public.\"escuelasProfesionales\" SET eliminado = TRUE WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id);

    @Modifying
    @Query(value = "UPDATE public.\"escuelasProfesionales\" SET activo = TRUE WHERE id = ?1", nativeQuery = true)
    public void activate(Integer id);

    @Modifying
    @Query(value = "UPDATE public.\"escuelasProfesionales\" SET activo = FALSE WHERE id = ?1", nativeQuery = true)
    public void deactivate(Integer id);
}
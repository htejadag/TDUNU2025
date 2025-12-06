package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEscuelaProfesionalRepository extends JpaRepository<EscuelaProfesionalModel, Integer> {

    public List<EscuelaProfesionalModel> findByEliminadoFalse();

    public Optional<EscuelaProfesionalModel> findByIdAndEliminadoFalse(Integer id);

    public EscuelaProfesionalModel findByNombre(String nombre);

    @Modifying
    @Query(value = "UPDATE public.escuelasProfesionales SET eliminado = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void delete(Integer id, String usuarioModificacion);
}
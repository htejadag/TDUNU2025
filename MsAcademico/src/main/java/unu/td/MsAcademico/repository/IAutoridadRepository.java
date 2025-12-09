package unu.td.MsAcademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.MsAcademico.model.entity.AutoridadModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAutoridadRepository extends JpaRepository<AutoridadModel, Integer> {

    public List<AutoridadModel> findByEliminadoFalse();

    public Optional<AutoridadModel> findByIdAndEliminadoFalse(Integer id);

    @Modifying
    @Query(value = "UPDATE public.autoridades SET eliminado = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id, String usuarioModificacion);
}
package unu.td.msacademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.msacademico.model.entity.AutoridadModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IAutoridadRepository extends JpaRepository<AutoridadModel, Integer> {

    public List<AutoridadModel> findByEliminadoFalse();

    public Optional<AutoridadModel> findByIdAndEliminadoFalse(Integer id);

    @Query(value = "SELECT * \n" +
            "FROM autoridades a\n" +
            "WHERE a.eliminado = FALSE\n" +
            "\tAND a.\"idTipoEntidad\" = ?1 AND a.\"idEntidad\" = ?2;", nativeQuery = true)
    public List<AutoridadModel> findByEntidad(Integer idTipoEntidad, Integer idEntidad);

    @Query(value = "SELECT * \n" +
            "FROM autoridades a\n" +
            "WHERE a.eliminado = FALSE\n" +
            "\tAND a.\"fechaFin\"  >= ?3 AND a.\"fechaInicio\" <= ?3\n" +
            "\tAND a.\"idTipoEntidad\" = ?1 AND a.\"idEntidad\" = ?2;", nativeQuery = true)
    public Optional<AutoridadModel> findByEntidadAndFecha(Integer idTipoEntidad, Integer idEntidad, LocalDate fecha);

    @Modifying
    @Query(value = "UPDATE public.autoridades SET eliminado = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id, String usuarioModificacion);

    @Modifying
    @Query(value = "UPDATE public.autoridades SET activo = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void activate(Integer id, String usuarioModificacion);

    @Modifying
    @Query(value = "UPDATE public.autoridades SET activo = FALSE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void deactivate(Integer id, String usuarioModificacion);
}
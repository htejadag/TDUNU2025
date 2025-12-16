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

    @Query(value = """
            SELECT EXISTS (
                SELECT 1
                FROM autoridades a
                WHERE a.eliminado = FALSE
                  AND (a."fechaFin" IS NULL OR a."fechaFin" >= ?4)
                  AND a."fechaInicio" <= ?3
                  AND a."idTipoEntidad" = ?1
                  AND a."idEntidad" = ?2
            )
            """, nativeQuery = true)
    public Boolean checkParameters(Integer idTipoEntidad, Integer idEntidad, LocalDate fechaInicio, LocalDate fechaFin);

    public List<AutoridadModel> findByEliminadoFalseOrderByFechaInicioDesc();

    @Modifying
    @Query(value = """
                UPDATE autoridades 
                SET activo = FALSE, "usuarioModificacion" = ?3, "fechaFin" = CURRENT_DATE 
                WHERE eliminado = FALSE 
                  AND "idTipoEntidad" = ?1 
                  AND "idEntidad" = ?2 
                  AND ( "fechaFin" IS NULL OR "fechaFin" >= CURRENT_DATE )
                  AND "fechaInicio" <= CURRENT_DATE
            """, nativeQuery = true)
    public void deactivateActivasActuales(Integer idTipoEntidad, Integer idEntidad, String usuarioModificacion);

    @Query(value = """
            SELECT EXISTS (
                SELECT 1
                FROM autoridades a
                WHERE a.eliminado = FALSE
                  AND a.activo = TRUE
                  AND a."idTipoEntidad" = ?1
                  AND a."idEntidad" = ?2
                  AND a."fechaInicio" <= CURRENT_DATE
                  AND (a."fechaFin" IS NULL OR a."fechaFin" >= CURRENT_DATE)
            )
            """, nativeQuery = true)
    public Boolean existsActiveActual(Integer idTipoEntidad, Integer idEntidad);


}
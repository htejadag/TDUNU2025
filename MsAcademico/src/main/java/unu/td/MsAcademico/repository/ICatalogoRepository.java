package unu.td.msacademico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unu.td.msacademico.model.entity.CatalogoModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICatalogoRepository extends JpaRepository<CatalogoModel, Integer> {

    public List<CatalogoModel> findByEliminadoFalse();

    public Optional<CatalogoModel> findByIdAndEliminadoFalse(Integer id);

    public List<CatalogoModel> findByCategoriaAndEliminadoFalse(String categoria);

    public Optional<CatalogoModel> findByCategoriaAndNombre(String categoria, String nombre);

    @Modifying
    @Query(value = "UPDATE public.catalogo SET eliminado = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id, String usuarioModificacion);

    @Modifying
    @Query(value = "UPDATE public.catalogo SET activo = TRUE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void activate(Integer id, String usuarioModificacion);

    @Modifying
    @Query(value = "UPDATE public.catalogo SET activo = FALSE, \"usuarioModificacion\" = ?2 WHERE id = ?1", nativeQuery = true)
    public void deactivate(Integer id, String usuarioModificacion);
}
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
    @Query(value = "UPDATE public.catalogo SET eliminado = TRUE WHERE id = ?1", nativeQuery = true)
    public void softDelete(Integer id);

    @Modifying
    @Query(value = "UPDATE public.catalogo SET activo = TRUE WHERE id = ?1", nativeQuery = true)
    public void activate(Integer id);

    @Modifying
    @Query(value = "UPDATE public.catalogo SET activo = FALSE WHERE id = ?1", nativeQuery = true)
    public void deactivate(Integer id);

    @Query(value = "SELECT c.codigo FROM catalogo c WHERE c.categoria = ?1 ORDER BY c.codigo DESC LIMIT 1", nativeQuery = true)
    public Integer getLastCodigoByCategoria(String categoria);

    @Query(value = "SELECT EXISTS (\n" +
                    "\tSELECT 1 FROM catalogo c WHERE c.categoria = ?1 AND c.orden = ?2\n" +
                    ") AS existe", nativeQuery = true)
    public Boolean existsByCategoriaAndOrden(String categoria, Integer orden);

    @Query(value = "SELECT c.orden FROM catalogo c WHERE c.categoria = ?1 ORDER BY c.orden DESC LIMIT 1", nativeQuery = true)
    public Integer getLastOrdenByCategoria(String categoria);
}
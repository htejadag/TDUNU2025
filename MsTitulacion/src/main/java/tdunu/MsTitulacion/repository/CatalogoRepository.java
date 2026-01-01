package tdunu.MsTitulacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsTitulacion.model.entity.Catalogo;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

    List<Catalogo> findByCategoriaAndActivoTrue(String categoria);
    Optional<Catalogo> findByCodigo(String codigo);

}
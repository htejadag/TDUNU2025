package tdunu.MsCatalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsCatalogo.model.entity.TipoCatalogoModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoCatalogoRepository extends JpaRepository<TipoCatalogoModel, Integer> {

    Optional<TipoCatalogoModel> findByCodigo(String codigo);
    
    List<TipoCatalogoModel> findByActivo(Boolean activo);
}

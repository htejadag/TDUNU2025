package tdunu.MsCatalogo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsCatalogo.model.entity.CatalogoModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatalogoRepository extends JpaRepository<CatalogoModel, Integer> {

    List<CatalogoModel> findByTipoCatalogoId(Integer tipoCatalogoId);
    
    List<CatalogoModel> findByTipoCatalogoIdAndActivo(Integer tipoCatalogoId, Boolean activo);
    
    Optional<CatalogoModel> findByTipoCatalogoIdAndCodigo(Integer tipoCatalogoId, String codigo);
    
    List<CatalogoModel> findByActivo(Boolean activo);
    
    List<CatalogoModel> findByTipoCatalogoIdOrderByOrdenAsc(Integer tipoCatalogoId);
}

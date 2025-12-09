package com.example.MsGeneral.Repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.MsGeneral.Model.Entidad.Catalogo;
import java.util.List;


public interface CatalogoRepository extends MongoRepository<Catalogo,String> {
    
    Optional<Catalogo> findTopByCategoriaOrderByCodigoDesc(String categoria);

    List<Catalogo> findByCategoria(String categoria);

}

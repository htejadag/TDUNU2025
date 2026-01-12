package com.example.MsGeneral.Repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MsGeneral.Model.Entidad.Catalogo;
import java.util.List;

@Repository
public interface CatalogoRepository extends MongoRepository<Catalogo,String> {
    
    Optional<Catalogo> findTopByCategoriaOrderByCodigoDesc(String categoria);

    List<Catalogo> findByCategoria(String categoria);

}

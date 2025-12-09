package com.example.MsGeneral.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.MsGeneral.Model.Entidad.Catalogo;

public interface CatalogoRepositorio extends MongoRepository<Catalogo,String> {
    
}

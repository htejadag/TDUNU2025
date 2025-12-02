package tdunu.MsTramite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tdunu.MsTramite.model.entity.TramiteModel;

public interface TramiteRepository extends JpaRepository<TramiteModel, Integer>{
    
}

package tdunu.MsTitulacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu.MsTitulacion.model.entity.TesisBorrador;

@Repository
public interface TesisBorradorRepository extends JpaRepository<TesisBorrador, Integer>{

}

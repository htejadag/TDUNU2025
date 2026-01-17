package tdunu.MsTitulacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu.MsTitulacion.model.entity.RevisionBorrador;

@Repository
public interface RevisionBorradorRepository extends JpaRepository<RevisionBorrador, Integer>{

    
}
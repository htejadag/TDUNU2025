package tdunu.MsTitulacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionBorrador extends JpaRepository<RevisionBorrador, Integer>{

    
}
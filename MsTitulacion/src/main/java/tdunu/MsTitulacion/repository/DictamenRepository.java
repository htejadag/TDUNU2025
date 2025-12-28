package src.main.java.tdunu.MsTitulacion.repository;

import src.main.java.tdunu.MsTitulacion.model.entity.DictamenActo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.JpaRepository;

import java.util.List;

@Repository

public interface DictamenRepository extends JpaRepository<DictamenActo, Long>{
    
}

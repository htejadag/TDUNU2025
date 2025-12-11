package tdunu.MsPosgrado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu.MsPosgrado.model.JuradoModel;

@Repository
public interface JuradoRepository extends JpaRepository<JuradoModel, Integer> {

}

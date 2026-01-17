package tdunu.MsTitulacion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tdunu.MsTitulacion.model.entity.Dictamen;

@Repository
public interface DictamenRepository extends JpaRepository<Dictamen, Integer>{

    Optional<Dictamen> findByResultadoCategoria(String categoria);

} 
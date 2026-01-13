package tdunu.MsPersona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tdunu.MsPersona.model.entity.PersonaModel;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaModel, Integer> {

    boolean existsByPerDni(String perDni);
    boolean existsByPerEmail(String perEmail);
}

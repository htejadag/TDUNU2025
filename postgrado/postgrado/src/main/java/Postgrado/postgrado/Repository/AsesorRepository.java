package Postgrado.postgrado.Repository;

import Postgrado.postgrado.Model.Asesor;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Integer> {

}

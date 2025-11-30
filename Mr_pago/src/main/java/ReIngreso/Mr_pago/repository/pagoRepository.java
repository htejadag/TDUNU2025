package ReIngreso.Mr_pago.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ReIngreso.Mr_pago.model.pagoModel;


@Repository
public interface pagoRepository extends JpaRepository<pagoModel, Integer> {

}



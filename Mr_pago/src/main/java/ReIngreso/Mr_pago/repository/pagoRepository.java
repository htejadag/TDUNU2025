package ReIngreso.Mr_pago.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ReIngreso.Mr_pago.model.pagoModel;

@Repository
public interface pagoRepository extends CrudRepository<pagoModel, Long> {
}

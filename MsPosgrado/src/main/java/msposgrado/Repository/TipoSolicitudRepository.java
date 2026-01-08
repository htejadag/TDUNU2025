package msposgrado.Repository;

import msposgrado.Model.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud, Integer> {
    Optional<TipoSolicitud> findByNombre(String nombre);
}

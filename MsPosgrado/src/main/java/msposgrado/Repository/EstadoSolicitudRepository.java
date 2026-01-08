package msposgrado.Repository;

import msposgrado.Model.EstadoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoSolicitudRepository extends JpaRepository<EstadoSolicitud, Integer> {
    Optional<EstadoSolicitud> findByNombre(String nombre);
}

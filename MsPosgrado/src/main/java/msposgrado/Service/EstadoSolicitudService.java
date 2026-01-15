package msposgrado.Service;

import msposgrado.Model.EstadoSolicitud;
import java.util.List;

public interface EstadoSolicitudService {
    List<EstadoSolicitud> listar();

    EstadoSolicitud obtenerPorId(Integer id);

    EstadoSolicitud guardar(EstadoSolicitud estadoSolicitud);

    void eliminar(Integer id);
}

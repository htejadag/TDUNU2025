package msposgrado.Service;

import msposgrado.Model.TipoSolicitud;
import java.util.List;

public interface TipoSolicitudService {
    List<TipoSolicitud> listar();

    TipoSolicitud obtenerPorId(Integer id);

    TipoSolicitud guardar(TipoSolicitud tipoSolicitud);

    void eliminar(Integer id);
}

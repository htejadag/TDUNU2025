package Postgrado.postgrado.Service;

import Postgrado.postgrado.Model.Solicitud;
import java.util.List;

public interface SolicitudService {

    Solicitud crear(Solicitud solicitud);

    List<Solicitud> listar();

    Solicitud obtenerPorId(Integer id);

    void eliminar(Integer id);
}

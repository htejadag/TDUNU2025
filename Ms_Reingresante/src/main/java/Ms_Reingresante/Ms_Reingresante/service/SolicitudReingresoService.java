package Ms_Reingresante.Ms_Reingresante.service;

import java.util.List;
import Ms_Reingresante.Ms_Reingresante.model.request.SolicitudReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.SolicitudReingresoResponse;

public interface SolicitudReingresoService {
    
    List<SolicitudReingresoResponse> listar();
    SolicitudReingresoResponse obtenerPorId(Integer id);
    SolicitudReingresoResponse guardar(SolicitudReingresoRequest solicitud);
    void eliminar(Integer id);
}
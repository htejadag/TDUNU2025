package tdunu.MsRevision.service;

import tdunu.MsTitulacion.model.request.RevisionRequest;
import tdunu.MsTitulacion.model.response.RevisionResponse;
import java.util.List;

public interface RevisionService {
    
    RevisionResponse registrarRevision(RevisionRequest request);
    
    List<RevisionResponse> listarHistorialPorProyecto(Integer idProyecto);
    
    List<RevisionResponse> listarRevisionesPorRevisor(Integer idRevisorUsuario);
}
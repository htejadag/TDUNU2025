package tdunu.MsRevision.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tdunu.MsTitulacion.exception.ResourceNotFoundException;
import tdunu.MsTitulacion.model.entity.Catalogo;
import tdunu.MsTitulacion.model.entity.RevisionHistorial;
import tdunu.MsTitulacion.model.request.RevisionRequest;
import tdunu.MsTitulacion.model.response.RevisionResponse;
import tdunu.MsTitulacion.repository.CatalogoRepository;
import tdunu.MsTitulacion.repository.RevisionRepository;
import tdunu.MsTitulacion.service.RevisionService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RevisionServiceImp implements RevisionService {

    @Autowired
    private RevisionRepository revisionRepository;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Override
    @Transactional
    public RevisionResponse registrarRevision(RevisionRequest request) {
        log.info("Registrando nueva revisión para proyecto ID: {}", request.getIdProyecto());

        // 1. Validar que los IDs de catálogo existan
        validarExistenciaCatalogo(request.getIdTipoFaseCat(), "Tipo de Fase");
        validarExistenciaCatalogo(request.getIdEstadoDictamenCat(), "Estado Dictamen");

        // 2. Mapear Request a Entidad
        RevisionHistorial revision = new RevisionHistorial();
        revision.setIdProyecto(request.getIdProyecto());
        revision.setIdRevisorUsuario(request.getIdRevisorUsuario());
        revision.setIdTipoFaseCat(request.getIdTipoFaseCat());
        revision.setComentarios(request.getComentarios());
        revision.setIdEstadoDictamenCat(request.getIdEstadoDictamenCat());
        revision.setIntentoNumero(request.getIntentoNumero());
        revision.setFechaLimiteAtencion(request.getFechaLimiteAtencion());
        // Fecha revisión se setea en @PrePersist o aquí manualmente
        revision.setFechaRevision(LocalDateTime.now());

        RevisionHistorial revisionGuardada = revisionRepository.save(revision);

        return mapToResponse(revisionGuardada);
    }

    @Override
    public List<RevisionResponse> listarHistorialPorProyecto(Integer idProyecto) {
        List<RevisionHistorial> historial = revisionRepository.findByIdProyectoOrderByFechaRevisionDesc(idProyecto);
        return historial.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<RevisionResponse> listarRevisionesPorRevisor(Integer idRevisorUsuario) {
        List<RevisionHistorial> revisiones = revisionRepository.findByIdRevisorUsuario(idRevisorUsuario);
        return revisiones.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // --- Métodos Auxiliares ---

    private void validarExistenciaCatalogo(Integer id, String nombreCampo) {
        if (id != null && !catalogoRepository.existsById(id)) {
            throw new ResourceNotFoundException("El ID de catálogo para '" + nombreCampo + "' no existe: " + id);
        }
    }

    private RevisionResponse mapToResponse(RevisionHistorial entity) {
        RevisionResponse response = new RevisionResponse();
        response.setIdRevision(entity.getIdRevision());
        response.setIdProyecto(entity.getIdProyecto());
        response.setIdRevisorUsuario(entity.getIdRevisorUsuario());
        
        // Mapeo Fase
        response.setIdTipoFaseCat(entity.getIdTipoFaseCat());
        response.setNombreTipoFase(obtenerValorCatalogo(entity.getIdTipoFaseCat()));

        response.setComentarios(entity.getComentarios());

        // Mapeo Estado
        response.setIdEstadoDictamenCat(entity.getIdEstadoDictamenCat());
        response.setNombreEstadoDictamen(obtenerValorCatalogo(entity.getIdEstadoDictamenCat()));

        response.setIntentoNumero(entity.getIntentoNumero());
        response.setFechaRevision(entity.getFechaRevision());
        response.setFechaLimiteAtencion(entity.getFechaLimiteAtencion());

        return response;
    }

    private String obtenerValorCatalogo(Integer id) {
        if (id == null) return "Desconocido";
        return catalogoRepository.findById(id)
                .map(Catalogo::getValor)
                .orElse("No definido");
    }
}
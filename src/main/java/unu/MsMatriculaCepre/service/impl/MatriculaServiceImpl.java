package unu.MsMatriculaCepre.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unu.MsMatriculaCepre.model.entity.Matricula;
import unu.MsMatriculaCepre.model.request.MatriculaRequest;
import unu.MsMatriculaCepre.model.response.MatriculaResponse;
import unu.MsMatriculaCepre.repository.MatriculaRepository;
import unu.MsMatriculaCepre.service.MatriculaService;
import unu.MsMatriculaCepre.util.EstadoMatricula;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class MatriculaServiceImpl implements MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MatriculaResponse> listar() {
        log.info("Listando todas las matrículas");
        return matriculaRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public MatriculaResponse obtenerPorId(Integer id) {
        log.info("Obteniendo matrícula por ID: {}", id);
        return matriculaRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));
    }

    @Override
    public List<MatriculaResponse> listarPorEstudiante(Integer estudianteId) {
        log.info("Listando matrículas por estudiante: {}", estudianteId);
        return matriculaRepository.findByEstudianteId(estudianteId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public List<MatriculaResponse> listarPorGrupo(Integer grupoId) {
        log.info("Listando matrículas por grupo: {}", grupoId);
        return matriculaRepository.findByGrupoId(grupoId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    @Transactional
    public MatriculaResponse guardar(MatriculaRequest request) {
        log.info("Guardando nueva matrícula - Estudiante: {}, Grupo: {}", 
                request.getEstudianteId(), request.getGrupoId());

        // Validar que no exista matrícula duplicada
        if (matriculaRepository.existsByEstudianteIdAndGrupoId(
                request.getEstudianteId(), request.getGrupoId())) {
            throw new RuntimeException("El estudiante ya está matriculado en este grupo");
        }

        // Crear entidad
        Matricula matricula = new Matricula();
        matricula.setEstudianteId(request.getEstudianteId());
        matricula.setGrupoId(request.getGrupoId());
        matricula.setObservaciones(request.getObservaciones());
        matricula.setSistemaOrigen(request.getSistemaOrigen());
        matricula.setEstado(EstadoMatricula.CONFIRMADA);
        matricula.setFechaMatricula(LocalDateTime.now());
        matricula.setAuUsuariocr(request.getUsuarioRegistro());
        matricula.setAuUsuariomd(request.getUsuarioRegistro());

        // Guardar
        Matricula saved = matriculaRepository.save(matricula);

        // Generar código después de obtener el ID
        saved.setCodigo(generarCodigo(saved.getMatriculaId()));
        saved = matriculaRepository.save(saved);

        log.info("Matrícula guardada con ID: {} y código: {}", saved.getMatriculaId(), saved.getCodigo());
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public MatriculaResponse actualizar(Integer id, MatriculaRequest request) {
        log.info("Actualizando matrícula ID: {}", id);

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        // Validar duplicado solo si cambia estudiante o grupo
        if (!matricula.getEstudianteId().equals(request.getEstudianteId()) ||
            !matricula.getGrupoId().equals(request.getGrupoId())) {
            
            if (matriculaRepository.existsByEstudianteIdAndGrupoIdAndMatriculaIdNot(
                    request.getEstudianteId(), request.getGrupoId(), id)) {
                throw new RuntimeException("El estudiante ya está matriculado en este grupo");
            }
        }

        // Actualizar campos
        matricula.setEstudianteId(request.getEstudianteId());
        matricula.setGrupoId(request.getGrupoId());
        matricula.setObservaciones(request.getObservaciones());
        matricula.setSistemaOrigen(request.getSistemaOrigen());
        matricula.setAuUsuariomd(request.getUsuarioRegistro());

        Matricula saved = matriculaRepository.save(matricula);
        log.info("Matrícula actualizada: {}", saved.getCodigo());
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public MatriculaResponse confirmar(Integer id) {
        log.info("Confirmando matrícula ID: {}", id);

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        matricula.setEstado(EstadoMatricula.CONFIRMADA);
        Matricula saved = matriculaRepository.save(matricula);

        log.info("Matrícula confirmada: {}", saved.getCodigo());
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public MatriculaResponse anular(Integer id, String motivo) {
        log.info("Anulando matrícula ID: {} - Motivo: {}", id, motivo);

        Matricula matricula = matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con ID: " + id));

        matricula.setEstado(EstadoMatricula.ANULADA);
        
        // Agregar motivo a observaciones
        String observacionActual = matricula.getObservaciones() != null ? 
                matricula.getObservaciones() + " | " : "";
        matricula.setObservaciones(observacionActual + "ANULADO: " + motivo);

        Matricula saved = matriculaRepository.save(matricula);
        log.info("Matrícula anulada: {}", saved.getCodigo());
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        log.info("Eliminando matrícula ID: {}", id);
        
        if (!matriculaRepository.existsById(id)) {
            throw new RuntimeException("Matrícula no encontrada con ID: " + id);
        }
        
        matriculaRepository.deleteById(id);
        log.info("Matrícula eliminada: {}", id);
    }

    @Override
    public boolean existeMatricula(Integer estudianteId, Integer grupoId) {
        return matriculaRepository.existsByEstudianteIdAndGrupoId(estudianteId, grupoId);
    }

    // ==================== MÉTODOS PRIVADOS ====================

    private MatriculaResponse convertToResponse(Matricula matricula) {
        return modelMapper.map(matricula, MatriculaResponse.class);
    }

    private String generarCodigo(Integer id) {
        String year = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
        return String.format("MAT-%s-%05d", year, id);
    }
}

package unu.MsMatriculaCepre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unu.MsMatriculaCepre.model.entity.Grupo;
import unu.MsMatriculaCepre.model.entity.Matricula;
import unu.MsMatriculaCepre.model.entity.MatriculaGrupo;
import unu.MsMatriculaCepre.model.request.GrupoRequest;
import unu.MsMatriculaCepre.model.request.MatriculaGrupoRequest;
import unu.MsMatriculaCepre.model.response.GrupoResponse;
import unu.MsMatriculaCepre.model.response.MatriculaGrupoResponse;
import unu.MsMatriculaCepre.repository.GrupoRepository;
import unu.MsMatriculaCepre.repository.MatriculaGrupoRepository;
import unu.MsMatriculaCepre.repository.MatriculaRepository;
import unu.MsMatriculaCepre.service.MatriculaGrupoService;
import unu.MsMatriculaCepre.util.Turno;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaGrupoServiceImpl implements MatriculaGrupoService {
    
    @Autowired
    private MatriculaGrupoRepository matriculaGrupoRepository;
    
    @Autowired
    private GrupoRepository grupoRepository;
    
    @Autowired
    private MatriculaRepository matriculaRepository;
    
    // ==================== GESTIÓN DE GRUPOS ====================
    
    @Override
    @Transactional
    public GrupoResponse crearGrupo(GrupoRequest request) {
        if (grupoRepository.existsByCodigo(request.getCodigo())) {
            throw new RuntimeException("Ya existe un grupo con el código: " + request.getCodigo());
        }
        
        Grupo grupo = new Grupo();
        grupo.setCodigo(request.getCodigo());
        grupo.setNombre(request.getNombre());
        grupo.setTurno(request.getTurno());
        grupo.setAula(request.getAula());
        grupo.setCapacidadMaxima(request.getCapacidadMaxima());
        grupo.setCapacidadActual(0);
        grupo.setHorario(request.getHorario());
        grupo.setDocente(request.getDocente());
        grupo.setProcesoAcademico(request.getProcesoAcademico());
        grupo.setEstado("ACTIVO");
        
        grupo = grupoRepository.save(grupo);
        return convertToGrupoResponse(grupo);
    }
    
    @Override
    @Transactional
    public GrupoResponse actualizarGrupo(Long id, GrupoRequest request) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        
        if (!grupo.getCodigo().equals(request.getCodigo()) && 
            grupoRepository.existsByCodigo(request.getCodigo())) {
            throw new RuntimeException("Ya existe otro grupo con el código: " + request.getCodigo());
        }
        
        grupo.setCodigo(request.getCodigo());
        grupo.setNombre(request.getNombre());
        grupo.setTurno(request.getTurno());
        grupo.setAula(request.getAula());
        grupo.setCapacidadMaxima(request.getCapacidadMaxima());
        grupo.setHorario(request.getHorario());
        grupo.setDocente(request.getDocente());
        grupo.setProcesoAcademico(request.getProcesoAcademico());
        
        grupo = grupoRepository.save(grupo);
        return convertToGrupoResponse(grupo);
    }
    
    @Override
    @Transactional
    public void eliminarGrupo(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        
        Long estudiantesActivos = matriculaGrupoRepository.countActiveByGrupoId(id);
        if (estudiantesActivos > 0) {
            throw new RuntimeException("No se puede eliminar el grupo porque tiene " + 
                                     estudiantesActivos + " estudiantes asignados");
        }
        
        grupo.setEstado("INACTIVO");
        grupoRepository.save(grupo);
    }
    
    @Override
    public GrupoResponse obtenerGrupoPorId(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        return convertToGrupoResponse(grupo);
    }
    
    @Override
    public List<GrupoResponse> listarGrupos() {
        return grupoRepository.findAll().stream()
                .map(this::convertToGrupoResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<GrupoResponse> listarGruposDisponibles(String procesoAcademico) {
        return grupoRepository.findGruposDisponibles(procesoAcademico).stream()
                .map(this::convertToGrupoResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<GrupoResponse> listarGruposDisponiblesPorTurno(String procesoAcademico, Turno turno) {
        return grupoRepository.findGruposDisponiblesPorTurno(procesoAcademico, turno).stream()
                .map(this::convertToGrupoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<GrupoResponse> listarGruposPorTurno(Turno turno) {
        return grupoRepository.findByTurnoAndEstado(turno, "ACTIVO").stream()
                .map(this::convertToGrupoResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<GrupoResponse> listarGruposPorProceso(String procesoAcademico) {
        return grupoRepository.findByProcesoAcademicoAndEstado(procesoAcademico, "ACTIVO").stream()
                .map(this::convertToGrupoResponse)
                .collect(Collectors.toList());
    }
    
    // ==================== GESTIÓN DE ASIGNACIONES ====================
    
    @Override
    @Transactional
    public MatriculaGrupoResponse asignarGrupo(MatriculaGrupoRequest request) {
        // Validar que la matrícula existe
        Matricula matricula = matriculaRepository.findById(request.getMatriculaId())
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada"));
        
        // Validar que el grupo existe y está activo
        Grupo grupo = grupoRepository.findById(request.getGrupoId())
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
        
        if (!"ACTIVO".equals(grupo.getEstado())) {
            throw new RuntimeException("El grupo no está activo");
        }
        
        // Validar capacidad del grupo
        if (grupo.getCapacidadActual() >= grupo.getCapacidadMaxima()) {
            throw new RuntimeException("El grupo ha alcanzado su capacidad máxima (" + 
                                     grupo.getCapacidadMaxima() + " estudiantes)");
        }
        
        // Verificar si ya tiene asignación activa
        if (matriculaGrupoRepository.existsByMatriculaMatriculaIdAndEstado(request.getMatriculaId(), "ACTIVO")) {
            throw new RuntimeException("El estudiante ya tiene un grupo asignado. " +
                                     "Debe reasignar o desasignar primero.");
        }
        
        // Crear la asignación
        MatriculaGrupo matriculaGrupo = new MatriculaGrupo();
        matriculaGrupo.setMatricula(matricula);
        matriculaGrupo.setGrupo(grupo);
        matriculaGrupo.setEstado("ACTIVO");
        matriculaGrupo.setObservaciones(request.getObservaciones());
        
        matriculaGrupo = matriculaGrupoRepository.save(matriculaGrupo);
        
        // Actualizar capacidad del grupo
        grupo.setCapacidadActual(grupo.getCapacidadActual() + 1);
        grupoRepository.save(grupo);
        
        return convertToMatriculaGrupoResponse(matriculaGrupo);
    }
    
    @Override
    @Transactional
    public MatriculaGrupoResponse reasignarGrupo(Long matriculaGrupoId, Long nuevoGrupoId) {
        MatriculaGrupo matriculaGrupoActual = matriculaGrupoRepository.findById(matriculaGrupoId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        if (!"ACTIVO".equals(matriculaGrupoActual.getEstado())) {
            throw new RuntimeException("La asignación no está activa");
        }
        
        Grupo nuevoGrupo = grupoRepository.findById(nuevoGrupoId)
                .orElseThrow(() -> new RuntimeException("Nuevo grupo no encontrado"));
        
        if (!"ACTIVO".equals(nuevoGrupo.getEstado())) {
            throw new RuntimeException("El nuevo grupo no está activo");
        }
        
        // Validar capacidad del nuevo grupo
        if (nuevoGrupo.getCapacidadActual() >= nuevoGrupo.getCapacidadMaxima()) {
            throw new RuntimeException("El nuevo grupo ha alcanzado su capacidad máxima");
        }
        
        // Validar que no sea el mismo grupo
        if (matriculaGrupoActual.getGrupo().getId().equals(nuevoGrupoId)) {
            throw new RuntimeException("El estudiante ya está asignado a este grupo");
        }
        
        // Marcar la asignación actual como reasignada
        Grupo grupoAnterior = matriculaGrupoActual.getGrupo();
        matriculaGrupoActual.setEstado("REASIGNADO");
        matriculaGrupoRepository.save(matriculaGrupoActual);
        
        // Crear nueva asignación
        MatriculaGrupo nuevaAsignacion = new MatriculaGrupo();
        nuevaAsignacion.setMatricula(matriculaGrupoActual.getMatricula());
        nuevaAsignacion.setGrupo(nuevoGrupo);
        nuevaAsignacion.setEstado("ACTIVO");
        nuevaAsignacion.setObservaciones("Reasignado desde " + grupoAnterior.getCodigo() + 
                                       " (" + grupoAnterior.getNombre() + ")");
        
        nuevaAsignacion = matriculaGrupoRepository.save(nuevaAsignacion);
        
        // Actualizar capacidades
        grupoAnterior.setCapacidadActual(grupoAnterior.getCapacidadActual() - 1);
        nuevoGrupo.setCapacidadActual(nuevoGrupo.getCapacidadActual() + 1);
        grupoRepository.save(grupoAnterior);
        grupoRepository.save(nuevoGrupo);
        
        return convertToMatriculaGrupoResponse(nuevaAsignacion);
    }
    
    @Override
    @Transactional
    public void desasignarGrupo(Long matriculaGrupoId) {
        MatriculaGrupo matriculaGrupo = matriculaGrupoRepository.findById(matriculaGrupoId)
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        
        if (!"ACTIVO".equals(matriculaGrupo.getEstado())) {
            throw new RuntimeException("La asignación no está activa");
        }
        
        // Cambiar estado a INACTIVO
        matriculaGrupo.setEstado("INACTIVO");
        matriculaGrupoRepository.save(matriculaGrupo);
        
        // Actualizar capacidad del grupo
        Grupo grupo = matriculaGrupo.getGrupo();
        grupo.setCapacidadActual(grupo.getCapacidadActual() - 1);
        grupoRepository.save(grupo);
    }
    
    // ==================== CONSULTAS ====================
    
    @Override
    public List<MatriculaGrupoResponse> obtenerPorMatricula(Integer matriculaId) {
        return matriculaGrupoRepository.findByMatriculaMatriculaId(matriculaId).stream()
                .map(this::convertToMatriculaGrupoResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public List<MatriculaGrupoResponse> obtenerEstudiantesPorGrupo(Long grupoId) {
        return matriculaGrupoRepository.findActiveByGrupoId(grupoId).stream()
                .map(this::convertToMatriculaGrupoResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public MatriculaGrupoResponse obtenerAsignacionActiva(Integer matriculaId) {
        MatriculaGrupo mg = matriculaGrupoRepository.findByMatriculaMatriculaIdAndEstado(matriculaId, "ACTIVO")
                .orElse(null);
        
        return mg != null ? convertToMatriculaGrupoResponse(mg) : null;
    }
    
    // ==================== CONVERSORES ====================
    
    private GrupoResponse convertToGrupoResponse(Grupo grupo) {
        GrupoResponse response = new GrupoResponse();
        response.setId(grupo.getId());
        response.setCodigo(grupo.getCodigo());
        response.setNombre(grupo.getNombre());
        response.setTurno(grupo.getTurno().name());
        response.setAula(grupo.getAula());
        response.setCapacidadMaxima(grupo.getCapacidadMaxima());
        response.setCapacidadActual(grupo.getCapacidadActual());
        response.setCuposDisponibles(grupo.getCapacidadMaxima() - grupo.getCapacidadActual());
        response.setHorario(grupo.getHorario());
        response.setDocente(grupo.getDocente());
        response.setProcesoAcademico(grupo.getProcesoAcademico());
        response.setEstado(grupo.getEstado());
        return response;
    }
    
    private MatriculaGrupoResponse convertToMatriculaGrupoResponse(MatriculaGrupo mg) {
        MatriculaGrupoResponse response = new MatriculaGrupoResponse();
        response.setId(mg.getId());
        response.setMatriculaId(mg.getMatricula().getMatriculaId());
        
        // Como Matricula no tiene nombre/dni directamente, usamos el estudianteId
        Matricula matricula = mg.getMatricula();
        response.setNombreEstudiante("Estudiante ID: " + matricula.getEstudianteId());
        response.setDniEstudiante(null); // No disponible en la entidad Matricula actual
        
        // Datos del grupo
        response.setGrupoId(mg.getGrupo().getId());
        response.setCodigoGrupo(mg.getGrupo().getCodigo());
        response.setNombreGrupo(mg.getGrupo().getNombre());
        response.setTurno(mg.getGrupo().getTurno().name());
        response.setAula(mg.getGrupo().getAula());
        response.setHorario(mg.getGrupo().getHorario());
        response.setDocente(mg.getGrupo().getDocente());
        response.setEstado(mg.getEstado());
        response.setFechaAsignacion(mg.getFechaAsignacion() != null ? 
                mg.getFechaAsignacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : null);
        response.setObservaciones(mg.getObservaciones());
        
        return response;
    }
}
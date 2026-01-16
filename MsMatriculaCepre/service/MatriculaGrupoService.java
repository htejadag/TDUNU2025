package unu.MsMatriculaCepre.service;

import unu.MsMatriculaCepre.model.request.GrupoRequest;
import unu.MsMatriculaCepre.model.request.MatriculaGrupoRequest;
import unu.MsMatriculaCepre.model.response.GrupoResponse;
import unu.MsMatriculaCepre.model.response.MatriculaGrupoResponse;
import unu.MsMatriculaCepre.util.Turno;

import java.util.List;

public interface MatriculaGrupoService {
    
    // Gestión de Grupos
    GrupoResponse crearGrupo(GrupoRequest request);
    GrupoResponse actualizarGrupo(Long id, GrupoRequest request);
    void eliminarGrupo(Long id);
    GrupoResponse obtenerGrupoPorId(Long id);
    List<GrupoResponse> listarGrupos();
    List<GrupoResponse> listarGruposDisponibles(String procesoAcademico);
    List<GrupoResponse> listarGruposDisponiblesPorTurno(String procesoAcademico, Turno turno);
    List<GrupoResponse> listarGruposPorProceso(String procesoAcademico);
    List<GrupoResponse> listarGruposPorTurno(Turno turno);
    
    // Gestión de Asignaciones
    MatriculaGrupoResponse asignarGrupo(MatriculaGrupoRequest request);
    MatriculaGrupoResponse reasignarGrupo(Long matriculaGrupoId, Long nuevoGrupoId);
    void desasignarGrupo(Long matriculaGrupoId);
    
    // Consultas
    List<MatriculaGrupoResponse> obtenerPorMatricula(Integer matriculaIdaId);
    List<MatriculaGrupoResponse> obtenerEstudiantesPorGrupo(Long grupoId);
    MatriculaGrupoResponse obtenerAsignacionActiva(Long matriculaId);
    MatriculaGrupoResponse obtenerAsignacionActiva(Integer matriculaId);
    List<MatriculaGrupoResponse> obtenerPorMatricula(Long matriculaId);
}
package com.service.MsTramiteTesis.client;

import com.service.MsTramiteTesis.model.dto.external.DocenteDTO;
import com.service.MsTramiteTesis.model.dto.external.EstudianteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Cliente REST para comunicarse con el Microservicio de Personas
 */
@Service
public class PersonasClient {

    private static final Logger logger = LoggerFactory.getLogger(PersonasClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ms.personas.url}")
    private String personasBaseUrl;

    /**
     * Obtener información de un estudiante por ID
     */
    public EstudianteDTO obtenerEstudiante(Long idEstudiante) {
        try {
            String url = personasBaseUrl + "/api/estudiantes/" + idEstudiante;
            logger.info("Consultando estudiante en: {}", url);

            EstudianteDTO estudiante = restTemplate.getForObject(url, EstudianteDTO.class);
            return estudiante;

        } catch (Exception e) {
            logger.error("Error al obtener estudiante con ID {}: {}", idEstudiante, e.getMessage());
            // Retornar un objeto con datos mínimos en caso de error
            EstudianteDTO fallback = new EstudianteDTO();
            fallback.setId(idEstudiante);
            fallback.setNombres("Estudiante");
            fallback.setApellidos("(No disponible)");
            return fallback;
        }
    }

    /**
     * Obtener información de un docente por ID
     */
    public DocenteDTO obtenerDocente(Long idDocente) {
        try {
            String url = personasBaseUrl + "/api/docentes/" + idDocente;
            logger.info("Consultando docente en: {}", url);

            DocenteDTO docente = restTemplate.getForObject(url, DocenteDTO.class);
            return docente;

        } catch (Exception e) {
            logger.error("Error al obtener docente con ID {}: {}", idDocente, e.getMessage());
            // Retornar un objeto con datos mínimos en caso de error
            DocenteDTO fallback = new DocenteDTO();
            fallback.setId(idDocente);
            fallback.setNombres("Docente");
            fallback.setApellidos("(No disponible)");
            return fallback;
        }
    }

    /**
     * Verificar si un estudiante existe
     */
    public boolean existeEstudiante(Long idEstudiante) {
        try {
            EstudianteDTO estudiante = obtenerEstudiante(idEstudiante);
            return estudiante != null && estudiante.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verificar si un docente existe
     */
    public boolean existeDocente(Long idDocente) {
        try {
            DocenteDTO docente = obtenerDocente(idDocente);
            return docente != null && docente.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    // ========== BÚSQUEDA POR CÓDIGO Y DNI ==========

    /**
     * Obtener información de un estudiante por código universitario
     */
    public EstudianteDTO obtenerEstudiantePorCodigo(String codigo) {
        try {
            String url = personasBaseUrl + "/api/estudiantes/buscar/codigo/" + codigo;
            logger.info("Consultando estudiante por código en: {}", url);

            EstudianteDTO estudiante = restTemplate.getForObject(url, EstudianteDTO.class);
            return estudiante;

        } catch (Exception e) {
            logger.error("Error al obtener estudiante con código {}: {}", codigo, e.getMessage());
            // Retornar un objeto con datos mínimos en caso de error
            EstudianteDTO fallback = new EstudianteDTO();
            fallback.setCodigo(codigo);
            fallback.setNombres("Estudiante");
            fallback.setApellidos("(No disponible)");
            return fallback;
        }
    }

    /**
     * Obtener información de un docente/coordinador por DNI
     */
    public DocenteDTO obtenerDocentePorDni(String dni) {
        try {
            String url = personasBaseUrl + "/api/docentes/buscar/dni/" + dni;
            logger.info("Consultando docente por DNI en: {}", url);

            DocenteDTO docente = restTemplate.getForObject(url, DocenteDTO.class);
            return docente;

        } catch (Exception e) {
            logger.error("Error al obtener docente con DNI {}: {}", dni, e.getMessage());
            // Retornar un objeto con datos mínimos en caso de error
            DocenteDTO fallback = new DocenteDTO();
            fallback.setDni(dni);
            fallback.setNombres("Docente");
            fallback.setApellidos("(No disponible)");
            return fallback;
        }
    }

    /**
     * Obtener información del coordinador (búsqueda por DNI)
     */
    public DocenteDTO obtenerCoordinador(String dni) {
        return obtenerDocentePorDni(dni);
    }

    /**
     * Verificar si un estudiante existe por código
     */
    public boolean existeEstudiantePorCodigo(String codigo) {
        try {
            EstudianteDTO estudiante = obtenerEstudiantePorCodigo(codigo);
            return estudiante != null && estudiante.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Verificar si un docente existe por DNI
     */
    public boolean existeDocentePorDni(String dni) {
        try {
            DocenteDTO docente = obtenerDocentePorDni(dni);
            return docente != null && docente.getId() != null;
        } catch (Exception e) {
            return false;
        }
    }
}

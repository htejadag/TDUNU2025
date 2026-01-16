package com.unu.ms.MsConsejo.DataLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.entity.Auditoria;
import com.unu.ms.MsConsejo.model.entity.Catalogo;
import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.repository.AsistenciaSesionRepository;
import com.unu.ms.MsConsejo.repository.AuditoriaRepository;
import com.unu.ms.MsConsejo.repository.CatalogoRepository;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

/**
 * Cargador de datos de prueba para consejos, sesiones y asistencias.
 * 
 * Componente de inicialización que carga automáticamente datos de prueba
 * en la base de datos durante el arranque de la aplicación. Populan los
 * catálogos de valores y crea consejos, sesiones, miembros y registros
 * de asistencia con datos realistas.
 * 
 * El proceso de carga está ordenado (Order annotation) para asegurar que
 * los catálogos se carguen primero, seguidos de los consejos y datos relacionados.
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Slf4j
@Configuration
public class ConsejoSesionDataLoader {

    /**
     * Carga los catálogos de valores (valores predefinidos) en la base de datos.
     * 
     * Incluye catálogos para:
     * - Estados generales (ACTIVO, INACTIVO, ANULADO, etc.)
     * - Tipos de sesión (ORDINARIA, EXTRAORDINARIA, SOLEMNE, etc.)
     * - Estados de asistencia (PRESENTE, AUSENTE, JUSTIFICADO, etc.)
     * - Cargos en el consejo (DECANO, SECRETARIO, DOCENTE, etc.)
     * 
     * @param catalogoRepository repositorio de catálogos
     * @return CommandLineRunner que ejecuta la carga de catálogos al iniciar
     */
    @Bean
    @Order(1)
    @Transactional
    CommandLineRunner cargarCatalogos(CatalogoRepository catalogoRepository) {
        return args -> {

            log.info("Inicio: cargando catálogos de datos");

            // Si ya existen catálogos, no cargar (carga idempotente)
            if (catalogoRepository.count() > 0) {
                log.info("Catálogos ya existen en la base de datos. Saltando carga");
                return;
            }

            List<Catalogo> catalogos = List.of(

                // ESTADO_GENERAL
                crearCatalogo(1, "ESTADO_GENERAL", "ACTIVO", "Entidad habilitada"),
                crearCatalogo(2, "ESTADO_GENERAL", "INACTIVO", "Entidad inhabilitada"),
                crearCatalogo(3, "ESTADO_GENERAL", "ANULADO", "Entidad anulada"),
                crearCatalogo(4, "ESTADO_GENERAL", "FINALIZADO", "Proceso concluido"),
                crearCatalogo(5, "ESTADO_GENERAL", "SUSPENDIDO", "Proceso suspendido"),

                // TIPO_SESION
                crearCatalogo(10, "TIPO_SESION", "ORDINARIA", "Sesión regular"),
                crearCatalogo(11, "TIPO_SESION", "EXTRAORDINARIA", "Sesión fuera de calendario"),
                crearCatalogo(12, "TIPO_SESION", "SOLEMNE", "Sesión protocolar"),
                crearCatalogo(13, "TIPO_SESION", "URGENTE", "Sesión de emergencia"),
                crearCatalogo(14, "TIPO_SESION", "VIRTUAL", "Sesión no presencial"),

                // ESTADO_ASISTENCIA
                crearCatalogo(20, "ESTADO_ASISTENCIA", "PRESENTE", "Asistencia confirmada"),
                crearCatalogo(21, "ESTADO_ASISTENCIA", "AUSENTE", "No asistió"),
                crearCatalogo(22, "ESTADO_ASISTENCIA", "JUSTIFICADO", "Falta justificada"),
                crearCatalogo(23, "ESTADO_ASISTENCIA", "TARDE", "Asistencia tardía"),
                crearCatalogo(24, "ESTADO_ASISTENCIA", "RETIRO", "Se retiró de la sesión"),

                // CARGO_CONSEJO
                crearCatalogo(30, "CARGO_CONSEJO", "DECANO", "Preside el consejo"),
                crearCatalogo(31, "CARGO_CONSEJO", "SECRETARIO", "Da fe de los acuerdos"),
                crearCatalogo(32, "CARGO_CONSEJO", "DOCENTE", "Miembro docente"),
                crearCatalogo(33, "CARGO_CONSEJO", "ESTUDIANTE", "Representante estudiantil"),
                crearCatalogo(34, "CARGO_CONSEJO", "INVITADO", "Participa sin voto")
            );

            catalogoRepository.saveAll(catalogos);
            log.info("Fin: {} catálogos cargados exitosamente", catalogos.size());
           
        };
    }

    /**
     * Helper privado para crear instancias de Catalogo.
     * 
     * @param id identificador del catálogo
     * @param categoria categoría a la que pertenece
     * @param valor valor del catálogo
     * @param descripcion descripción detallada del valor
     * @return instancia de Catalogo configurada
     */
    private Catalogo crearCatalogo(Integer id, String categoria, String valor, String descripcion) {
        Catalogo c = new Catalogo();
        c.setIdCatalogo(id);
        c.setCategoria(categoria);
        c.setValor(valor);
        c.setDescripcion(descripcion);
        return c;
    }

    /**
     * Carga los datos de prueba principales: consejos, miembros, sesiones y asistencias.
     * 
     * Crea datos realistas incluyendo:
     * - 2 consejos (Ingeniería y Ciencias Económicas)
     * - 20 miembros (10 por consejo con diferentes cargos)
     * - 20 sesiones (10 por consejo con fechas variadas)
     * - +200 registros de asistencia (uno por miembro y sesión)
     * - +200 registros de auditoría (capturando la creación de asistencias)
     * 
     * @param consejoRepository repositorio de consejos
     * @param miembroRepository repositorio de miembros
     * @param sesionRepository repositorio de sesiones
     * @param asistenciaRepository repositorio de asistencias
     * @param auditoriaRepository repositorio de auditoría
     * @return CommandLineRunner que ejecuta la carga de datos al iniciar
     */
    @Bean
    @Order(2)
    @Transactional
    CommandLineRunner cargarDatos(
            ConsejoRepository consejoRepository,
            MiembroConsejoRepository miembroRepository,
            SesionConsejoRepository sesionRepository,
            AsistenciaSesionRepository asistenciaRepository,
            AuditoriaRepository auditoriaRepository
    ) {
        return args -> {

            log.info("Inicio: cargando datos de test (consejos, sesiones, miembros, asistencias)");

            // Si ya existen consejos, no cargar (carga idempotente)
            if (consejoRepository.count() > 0) {
                log.info("Datos de test ya existen en la base de datos. Saltando carga");
                return;
            }

            Random random = new Random();

            // CONSEJOS (2)
            log.debug("Creando consejos de prueba");
            ConsejoModel consejoIng = crearConsejo(
                    "Consejo de Facultad de Ingeniería",
                    "Órgano colegiado responsable de decisiones académicas de ingeniería"
            );

            ConsejoModel consejoEco = crearConsejo(
                    "Consejo de Facultad de Ciencias Económicas",
                    "Órgano colegiado responsable de decisiones académicas de economía"
            );

            consejoRepository.saveAll(List.of(consejoIng, consejoEco));
            log.debug("2 consejos creados");

           
            // MIEMBROS (20)
            log.debug("Creando miembros del consejo");
            List<MiembroConsejoModel> miembros = new ArrayList<>();

            int personaId = 1000;
            for (ConsejoModel consejo : List.of(consejoIng, consejoEco)) {
                for (int i = 1; i <= 10; i++) {
                    MiembroConsejoModel m = new MiembroConsejoModel();
                    m.setConsejo(consejo);
                    m.setIdPersona(personaId++);
                    // Primer miembro es DECANO, segundo es SECRETARIO, resto son DOCENTES
                    m.setIdCargo(i == 1 ? 30 : (i == 2 ? 31 : 32));
                    m.setFechaInicio(LocalDate.of(2023, 1, 1));
                    miembros.add(m);
                }
            }
            miembroRepository.saveAll(miembros);
            log.debug("20 miembros creados");

            // SESIONES (20)
            log.debug("Creando sesiones de consejo");
            List<SesionConsejoModel> sesiones = new ArrayList<>();

            int sesionNum = 1;
            for (ConsejoModel consejo : List.of(consejoIng, consejoEco)) {
                for (int i = 1; i <= 10; i++) {
                    SesionConsejoModel s = new SesionConsejoModel();
                    s.setConsejo(consejo);
                    s.setNumeroSesion("SES-" + consejo.getIdConsejo() + "-2024-" + sesionNum++);
                    s.setNombreSesion(i % 2 == 0 ? "Sesión Ordinaria" : "Sesión Extraordinaria");
                    s.setFechaSesion(LocalDate.of(2024, (i % 12) + 1, (i * 2) % 28 + 1));
                    // ORDINARIA (10) o EXTRAORDINARIA (11)
                    s.setIdTipoSesion(i % 2 == 0 ? 10 : 11);
                    s.setDescripcion("Análisis y aprobación de asuntos académicos y administrativos");
                    s.setIdEstado(1); // ACTIVO
                    s.setUsuarioRegistro(10);
                    sesiones.add(s);
                }
            }
            sesionRepository.saveAll(sesiones);
            log.debug("20 sesiones creadas");

            // ASISTENCIAS (+200)
            log.debug("Creando registros de asistencia");
            List<AsistenciaSesionModel> asistencias = new ArrayList<>();

            for (SesionConsejoModel sesion : sesiones) {
                for (MiembroConsejoModel miembro : miembros) {

                    // Solo crear asistencias para miembros del mismo consejo
                    if (!miembro.getConsejo().getIdConsejo()
                            .equals(sesion.getConsejo().getIdConsejo())) {
                        continue;
                    }

                    AsistenciaSesionModel a = new AsistenciaSesionModel();
                    a.setSesion(sesion);
                    a.setMiembro(miembro);

                    // Lógica: DECANO siempre PRESENTE, otros con probabilidad de faltar o justificarse
                    int estadoAsistencia;
                    if (miembro.getIdCargo() == 30) { // DECANO
                        estadoAsistencia = 20; // PRESENTE
                    } else {
                        int r = random.nextInt(100);
                        // 80% presente, 10% justificado, 10% ausente
                        estadoAsistencia = r < 80 ? 20 : (r < 90 ? 22 : 21);
                    }

                    a.setIdEstadoAsistencia(estadoAsistencia);
                    a.setUsuarioRegistro(10);
                    asistencias.add(a);
                }
            }
            asistenciaRepository.saveAll(asistencias);
            log.debug("{} registros de asistencia creados", asistencias.size());

             // AUDITORÍA (+200)
            log.debug("Creando registros de auditoría");
            List<Auditoria> auditorias = new ArrayList<>();

            asistencias.forEach(asistencia -> {
                Auditoria au = new Auditoria();
                au.setNombreEntidad("ASISTENCIAS");
                au.setEntidadId(asistencia.getIdAsistencia());
                au.setAccion("CREATE");
                au.setUsuarioId(10);
                au.setDatosDespues("Asistencia creado");
                au.setObservacion("Carga inicial");
                auditorias.add(au);
            });
            auditoriaRepository.saveAll(auditorias);
            log.debug("{} registros de auditoría creados", auditorias.size());

            log.info("Fin: datos de test cargados exitosamente");
        };
    }

    /**
     * Helper privado para crear instancias de ConsejoModel.
     * 
     * @param nombre nombre del consejo
     * @param descripcion descripción del consejo
     * @return instancia de ConsejoModel configurada con estado ACTIVO
     */
    private ConsejoModel crearConsejo(String nombre, String descripcion) {
        ConsejoModel c = new ConsejoModel();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setIdEstado(1); // ACTIVO
        return c;
    }
    
}
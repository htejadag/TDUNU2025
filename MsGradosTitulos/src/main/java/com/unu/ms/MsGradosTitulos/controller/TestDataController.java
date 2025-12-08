package com.unu.ms.MsGradosTitulos.controller;

import com.unu.ms.MsGradosTitulos.model.entity.Expediente;
import com.unu.ms.MsGradosTitulos.model.entity.Resolucion;
import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticulo;
import com.unu.ms.MsGradosTitulos.model.entity.Seguimiento;
import com.unu.ms.MsGradosTitulos.repository.ExpedienteRepository;
import com.unu.ms.MsGradosTitulos.repository.ResolucionArticuloRepository;
import com.unu.ms.MsGradosTitulos.repository.ResolucionRepository;
import com.unu.ms.MsGradosTitulos.repository.SeguimientoRepository;
import com.unu.ms.MsGradosTitulos.util.ResponseBase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/test-data")
@Tag(name = "Datos de Prueba", description = "Endpoints para cargar datos de ejemplo")
public class TestDataController {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ResolucionRepository resolucionRepository;

    @Autowired
    private ResolucionArticuloRepository resolucionArticuloRepository;

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Operation(summary = "Cargar datos de prueba", description = "Carga datos de ejemplo en todas las tablas (5 expedientes, 5 resoluciones, 12 artículos, 15 seguimientos)")
    @PostMapping("/cargar")
    public ResponseBase<Map<String, Integer>> cargarDatosPrueba() {
        log.info("Iniciando carga de datos de prueba");
        Map<String, Integer> resultado = new HashMap<>();

        try {
            // Limpiar datos existentes primero
            log.info("Limpiando datos existentes...");
            resolucionArticuloRepository.deleteAll();
            resolucionRepository.deleteAll();
            seguimientoRepository.deleteAll();
            expedienteRepository.deleteAll();

            // Crear expedientes
            log.info("Insertando expedientes...");
            Expediente exp1 = expedienteRepository.save(Expediente.builder()
                .codigoExpediente("EXP-2025-001")
                .idPersona(1)
                .idEstado(1)
                .descripcion("Expediente para trámite de grado académico de Licenciatura en Ciencias de la Computación")
                .fechaApertura(LocalDate.of(2025, 1, 15))
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Expediente exp2 = expedienteRepository.save(Expediente.builder()
                .codigoExpediente("EXP-2025-002")
                .idPersona(2)
                .idEstado(1)
                .descripcion("Expediente para trámite de título profesional de Ingeniería de Sistemas")
                .fechaApertura(LocalDate.of(2025, 2, 20))
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Expediente exp3 = expedienteRepository.save(Expediente.builder()
                .codigoExpediente("EXP-2025-003")
                .idPersona(3)
                .idEstado(2)
                .descripcion("Expediente para reconocimiento de estudios realizados en institución extranjera")
                .fechaApertura(LocalDate.of(2025, 3, 10))
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Expediente exp4 = expedienteRepository.save(Expediente.builder()
                .codigoExpediente("EXP-2025-004")
                .idPersona(4)
                .idEstado(1)
                .descripcion("Expediente para trámite de grado de Maestría en Administración")
                .fechaApertura(LocalDate.of(2025, 1, 25))
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Expediente exp5 = expedienteRepository.save(Expediente.builder()
                .codigoExpediente("EXP-2025-005")
                .idPersona(5)
                .idEstado(3)
                .descripcion("Expediente cerrado - Grado académico otorgado")
                .fechaApertura(LocalDate.of(2025, 1, 5))
                .fechaCierre(LocalDate.of(2025, 1, 12))
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            resultado.put("expedientes", 5);
            log.info("Expedientes insertados: 5");

            // Crear resoluciones
            log.info("Insertando resoluciones...");
            Resolucion res1 = resolucionRepository.save(Resolucion.builder()
                .numeroResolucion("RES-2025-001")
                .idExpediente(exp1.getIdExpediente())
                .idSolicitud(1)
                .idEstado(1)
                .idTipoResolucion(1)
                .fechaEmision(LocalDate.of(2025, 1, 20))
                .resumen("Resolución de aprobación de grado académico de Licenciatura")
                .fundamento("Según lo establecido en el reglamento académico vigente y habiendo cumplido con todos los requisitos exigidos")
                .articuladoGeneral("Se aprueba el otorgamiento del grado académico de Licenciado en Ciencias de la Computación")
                .aprobadoEnSesion(1)
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Resolucion res2 = resolucionRepository.save(Resolucion.builder()
                .numeroResolucion("RES-2025-002")
                .idExpediente(exp2.getIdExpediente())
                .idSolicitud(2)
                .idEstado(1)
                .idTipoResolucion(1)
                .fechaEmision(LocalDate.of(2025, 2, 25))
                .resumen("Resolución de aprobación de título profesional de Ingeniero")
                .fundamento("Cumpliendo con todos los requisitos académicos y administrativos establecidos en el reglamento")
                .articuladoGeneral("Se aprueba el otorgamiento del título profesional de Ingeniero de Sistemas")
                .aprobadoEnSesion(2)
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Resolucion res3 = resolucionRepository.save(Resolucion.builder()
                .numeroResolucion("RES-2025-003")
                .idExpediente(exp3.getIdExpediente())
                .idSolicitud(3)
                .idEstado(2)
                .idTipoResolucion(2)
                .fechaEmision(LocalDate.of(2025, 3, 15))
                .resumen("Resolución en proceso de revisión para reconocimiento de estudios")
                .fundamento("Se está evaluando la documentación presentada y su equivalencia con el plan de estudios nacional")
                .articuladoGeneral("Pendiente de aprobación final por el consejo académico")
                .aprobadoEnSesion(3)
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Resolucion res4 = resolucionRepository.save(Resolucion.builder()
                .numeroResolucion("RES-2025-004")
                .idExpediente(exp4.getIdExpediente())
                .idSolicitud(4)
                .idEstado(1)
                .idTipoResolucion(1)
                .fechaEmision(LocalDate.of(2025, 2, 5))
                .resumen("Resolución de aprobación de grado de Maestría")
                .fundamento("Habiendo sustentado exitosamente la tesis de investigación y cumplido todos los requisitos")
                .articuladoGeneral("Se aprueba el otorgamiento del grado de Maestro en Administración de Empresas")
                .aprobadoEnSesion(4)
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            Resolucion res5 = resolucionRepository.save(Resolucion.builder()
                .numeroResolucion("RES-2025-005")
                .idExpediente(exp5.getIdExpediente())
                .idSolicitud(5)
                .idEstado(3)
                .idTipoResolucion(1)
                .fechaEmision(LocalDate.of(2025, 1, 10))
                .resumen("Resolución ejecutada - Diploma entregado")
                .fundamento("Resolución aprobada y ejecutada según normativa vigente")
                .articuladoGeneral("Grado académico otorgado y registrado en los libros oficiales")
                .aprobadoEnSesion(1)
                .idUsuarioCreo(1)
                .fechaCreacion(LocalDateTime.now())
                .build());

            resultado.put("resoluciones", 5);
            log.info("Resoluciones insertadas: 5");

            // Crear artículos de resolución
            log.info("Insertando artículos de resolución...");
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res1.getIdResolucion()).numeroArticulo(1).titulo("Artículo Primero")
                .contenido("Otorgar el grado académico de Licenciado en Ciencias de la Computación al estudiante Juan Carlos Pérez García, carné 2021-001").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res1.getIdResolucion()).numeroArticulo(2).titulo("Artículo Segundo")
                .contenido("Expedir el diploma correspondiente con las formalidades legales establecidas").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res1.getIdResolucion()).numeroArticulo(3).titulo("Artículo Tercero")
                .contenido("Registrar el presente acto académico en los libros y sistemas oficiales de la universidad").build());

            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res2.getIdResolucion()).numeroArticulo(1).titulo("Artículo Primero")
                .contenido("Otorgar el título profesional de Ingeniero de Sistemas a María Fernanda López Martínez, carné 2020-045").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res2.getIdResolucion()).numeroArticulo(2).titulo("Artículo Segundo")
                .contenido("Registrar el título en los libros correspondientes y en el sistema nacional de títulos").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res2.getIdResolucion()).numeroArticulo(3).titulo("Artículo Tercero")
                .contenido("Comunicar esta resolución a las instancias correspondientes para los fines legales").build());

            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res3.getIdResolucion()).numeroArticulo(1).titulo("Artículo Primero")
                .contenido("Reconocer los estudios de Ingeniería Civil realizados en la Universidad Politécnica de Madrid, España").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res3.getIdResolucion()).numeroArticulo(2).titulo("Artículo Segundo")
                .contenido("Establecer las asignaturas complementarias que deberá cursar para obtener la equivalencia completa").build());

            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res4.getIdResolucion()).numeroArticulo(1).titulo("Artículo Primero")
                .contenido("Otorgar el grado académico de Maestro en Administración de Empresas a Roberto Sánchez Díaz").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res4.getIdResolucion()).numeroArticulo(2).titulo("Artículo Segundo")
                .contenido("Expedir el diploma de maestría correspondiente").build());

            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res5.getIdResolucion()).numeroArticulo(1).titulo("Artículo Primero")
                .contenido("Dar por ejecutada la presente resolución").build());
            resolucionArticuloRepository.save(ResolucionArticulo.builder()
                .idResolucion(res5.getIdResolucion()).numeroArticulo(2).titulo("Artículo Segundo")
                .contenido("Archivar el expediente en el área correspondiente").build());

            resultado.put("articulos", 12);
            log.info("Artículos insertados: 12");

            // Crear seguimientos
            log.info("Insertando seguimientos...");
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp1.getIdExpediente()).idEstado(1)
                .comentario("Expediente recibido y registrado en el sistema").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(10)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp1.getIdExpediente()).idEstado(1)
                .comentario("Revisión inicial completada - Documentación completa").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(8)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp1.getIdExpediente()).idEstado(2)
                .comentario("Expediente aprobado para elaboración de resolución").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(5)).build());

            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp2.getIdExpediente()).idEstado(1)
                .comentario("Expediente en proceso de revisión inicial").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(15)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp2.getIdExpediente()).idEstado(2)
                .comentario("Documentación verificada y aprobada").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(12)).build());

            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp3.getIdExpediente()).idEstado(1)
                .comentario("Expediente recibido - Requiere documentación adicional").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(20)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp3.getIdExpediente()).idEstado(1)
                .comentario("Documentación complementaria recibida").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(18)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp3.getIdExpediente()).idEstado(2)
                .comentario("En proceso de evaluación por comisión académica").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(10)).build());

            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(2).entidadId(res1.getIdResolucion()).idEstado(1)
                .comentario("Resolución elaborada y enviada para revisión legal").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(4)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(2).entidadId(res1.getIdResolucion()).idEstado(2)
                .comentario("Resolución aprobada en sesión de consejo universitario").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(2)).build());

            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(2).entidadId(res2.getIdResolucion()).idEstado(1)
                .comentario("Resolución en proceso de elaboración").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(6)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(2).entidadId(res2.getIdResolucion()).idEstado(2)
                .comentario("Resolución aprobada y firmada por autoridades").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(3)).build());

            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp4.getIdExpediente()).idEstado(1)
                .comentario("Expediente recibido para grado de maestría").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(7)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(1).entidadId(exp5.getIdExpediente()).idEstado(3)
                .comentario("Expediente cerrado - Trámite finalizado exitosamente").idUsuario(1).fechaRegistro(LocalDateTime.now().minusDays(1)).build());
            seguimientoRepository.save(Seguimiento.builder().idEntidadCatalogo(2).entidadId(res5.getIdResolucion()).idEstado(3)
                .comentario("Diploma entregado al graduado").idUsuario(1).fechaRegistro(LocalDateTime.now()).build());

            resultado.put("seguimientos", 15);
            log.info("Seguimientos insertados: 15");

            log.info("Carga de datos de prueba completada exitosamente");
            return ResponseBase.ok("Datos de prueba cargados exitosamente", resultado);

        } catch (Exception e) {
            log.error("Error al cargar datos de prueba: {}", e.getMessage(), e);
            return ResponseBase.error("Error al cargar datos: " + e.getMessage());
        }
    }

    @Operation(summary = "Limpiar datos de prueba", description = "Elimina todos los datos de las tablas")
    @PostMapping("/limpiar")
    public ResponseBase<String> limpiarDatos() {
        try {
            log.info("Iniciando limpieza de datos...");
            resolucionArticuloRepository.deleteAll();
            log.info("Artículos eliminados");
            resolucionRepository.deleteAll();
            log.info("Resoluciones eliminadas");
            expedienteRepository.deleteAll();
            log.info("Expedientes eliminados");
            seguimientoRepository.deleteAll();
            log.info("Seguimientos eliminados");
            
            log.info("Limpieza de datos completada exitosamente");
            return ResponseBase.ok("Todos los datos han sido eliminados", null);
        } catch (Exception e) {
            log.error("Error al limpiar datos: {}", e.getMessage(), e);
            return ResponseBase.error("Error al limpiar datos: " + e.getMessage());
        }
    }
}

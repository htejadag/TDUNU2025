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

@Configuration
public class ConsejoSesionDataLoader {

    // CATÁLOGO
    @Bean
    @Order(1)
    @Transactional
    CommandLineRunner cargarCatalogos(CatalogoRepository catalogoRepository) {
        return args -> {

            if (catalogoRepository.count() > 0) return;

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
           
        };
    }

    private Catalogo crearCatalogo(Integer id, String categoria, String valor, String descripcion) {
        Catalogo c = new Catalogo();
        c.setIdCatalogo(id);
        c.setCategoria(categoria);
        c.setValor(valor);
        c.setDescripcion(descripcion);
        return c;
    }


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

            if (consejoRepository.count() > 0) return;

            Random random = new Random();

            // CONSEJOS (2)
            ConsejoModel consejoIng = crearConsejo(
                    "Consejo de Facultad de Ingeniería",
                    "Órgano colegiado responsable de decisiones académicas de ingeniería"
            );

            ConsejoModel consejoEco = crearConsejo(
                    "Consejo de Facultad de Ciencias Económicas",
                    "Órgano colegiado responsable de decisiones académicas de economía"
            );

            consejoRepository.saveAll(List.of(consejoIng, consejoEco));

           
            // MIEMBROS (20)
            List<MiembroConsejoModel> miembros = new ArrayList<>();

            int personaId = 1000;
            for (ConsejoModel consejo : List.of(consejoIng, consejoEco)) {
                for (int i = 1; i <= 10; i++) {
                    MiembroConsejoModel m = new MiembroConsejoModel();
                    m.setConsejo(consejo);
                    m.setIdPersona(personaId++);
                    m.setIdCargo(i == 1 ? 30 : (i == 2 ? 31 : 32)); // DECANO, SECRETARIO, DOCENTE
                    m.setFechaInicio(LocalDate.of(2023, 1, 1));
                    miembros.add(m);
                }
            }
            miembroRepository.saveAll(miembros);

            // SESIONES (20)
            List<SesionConsejoModel> sesiones = new ArrayList<>();

            int sesionNum = 1;
            for (ConsejoModel consejo : List.of(consejoIng, consejoEco)) {
                for (int i = 1; i <= 10; i++) {
                    SesionConsejoModel s = new SesionConsejoModel();
                    s.setConsejo(consejo);
                    s.setNumeroSesion("SES-" + consejo.getIdConsejo() + "-2024-" + sesionNum++);
                    s.setNombreSesion(i % 2 == 0 ? "Sesión Ordinaria" : "Sesión Extraordinaria");
                    s.setFechaSesion(LocalDate.of(2024, (i % 12) + 1, (i * 2) % 28 + 1));
                    s.setIdTipoSesion(i % 2 == 0 ? 10 : 11); // ORDINARIA / EXTRAORDINARIA
                    s.setDescripcion("Análisis y aprobación de asuntos académicos y administrativos");
                    s.setIdEstado(1); // ACTIVO
                    s.setUsuarioRegistro(10);
                    sesiones.add(s);
                }
            }
            sesionRepository.saveAll(sesiones);

            // ASISTENCIAS (+200)
            List<AsistenciaSesionModel> asistencias = new ArrayList<>();

            for (SesionConsejoModel sesion : sesiones) {
                for (MiembroConsejoModel miembro : miembros) {

                    if (!miembro.getConsejo().getIdConsejo()
                            .equals(sesion.getConsejo().getIdConsejo())) {
                        continue;
                    }

                    AsistenciaSesionModel a = new AsistenciaSesionModel();
                    a.setSesion(sesion);
                    a.setMiembro(miembro);

                    int estadoAsistencia;
                    if (miembro.getIdCargo() == 30) { // DECANO
                        estadoAsistencia = 20; // PRESENTE
                    } else {
                        int r = random.nextInt(100);
                        estadoAsistencia = r < 80 ? 20 : (r < 90 ? 22 : 21);
                    }

                    a.setIdEstadoAsistencia(estadoAsistencia);
                    a.setUsuarioRegistro(10);
                    asistencias.add(a);
                }
            }
            asistenciaRepository.saveAll(asistencias);

             // AUDITORÍA (+200)
            List<Auditoria> auditorias = new ArrayList<>();

            asistencias.forEach(seg -> {
                Auditoria au = new Auditoria();
                au.setNombreEntidad("ASISTENCIAS");
                au.setEntidadId(seg.getIdAsistencia());
                au.setAccion("CREATE");
                au.setUsuarioId(10);
                au.setDatosDespues("Asistencia creado");
                au.setObservacion("Carga inicial");
                auditorias.add(au);
            });
            auditoriaRepository.saveAll(auditorias);

        };
    }

    private ConsejoModel crearConsejo(String nombre, String descripcion) {
        ConsejoModel c = new ConsejoModel();
        c.setNombre(nombre);
        c.setDescripcion(descripcion);
        c.setIdEstado(1); // ACTIVO
        return c;
    }
    
}
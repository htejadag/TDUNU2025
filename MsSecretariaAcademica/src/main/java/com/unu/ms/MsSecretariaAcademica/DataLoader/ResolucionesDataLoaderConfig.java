package com.unu.ms.MsSecretariaAcademica.DataLoader;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;
import com.unu.ms.MsSecretariaAcademica.model.entity.Catalogo;
import com.unu.ms.MsSecretariaAcademica.model.entity.ExpedienteModel;
import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionArticuloModel;
import com.unu.ms.MsSecretariaAcademica.model.entity.ResolucionModel;
import com.unu.ms.MsSecretariaAcademica.model.entity.Seguimiento;
import com.unu.ms.MsSecretariaAcademica.model.entity.SolicitudModel;
import com.unu.ms.MsSecretariaAcademica.repository.AuditoriaRepository;
import com.unu.ms.MsSecretariaAcademica.repository.CatalogoRepository;
import com.unu.ms.MsSecretariaAcademica.repository.ExpedienteRepository;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionArticuloRepository;
import com.unu.ms.MsSecretariaAcademica.repository.ResolucionRepository;
import com.unu.ms.MsSecretariaAcademica.repository.SeguimientoRepository;
import com.unu.ms.MsSecretariaAcademica.repository.SolicitudRepository;

import jakarta.transaction.Transactional;

@Configuration
public class ResolucionesDataLoaderConfig {

    // CATÁLOGO
    @Bean
    @Order(1)
    @Transactional
    CommandLineRunner cargarCatalogo(CatalogoRepository catalogoRepository) {
        return args -> {

            if (catalogoRepository.count() > 0) return;

            List<Catalogo> catalogos = List.of(

                // ESTADO_GENERAL
                crearCatalogo(1, "ESTADO_GENERAL", "REGISTRADO", "Registro inicial"),
                crearCatalogo(2, "ESTADO_GENERAL", "EN_PROCESO", "En evaluación"),
                crearCatalogo(3, "ESTADO_GENERAL", "APROBADO", "Aprobado"),
                crearCatalogo(4, "ESTADO_GENERAL", "OBSERVADO", "Observado"),
                crearCatalogo(5, "ESTADO_GENERAL", "FINALIZADO", "Proceso concluido"),

                // TIPO_SOLICITUD
                crearCatalogo(10, "TIPO_SOLICITUD", "PETICION", "Petición administrativa"),
                crearCatalogo(11, "TIPO_SOLICITUD", "RECLAMO", "Reclamo formal"),
                crearCatalogo(12, "TIPO_SOLICITUD", "APELACION", "Apelación administrativa"),
                crearCatalogo(13, "TIPO_SOLICITUD", "INFORME", "Solicitud de informe"),
                crearCatalogo(14, "TIPO_SOLICITUD", "OTRO", "Otro tipo"),

                // TIPO_RESOLUCION
                crearCatalogo(20, "TIPO_RESOLUCION", "DECANAL", "Resolución decanal"),
                crearCatalogo(21, "TIPO_RESOLUCION", "RECTORAL", "Resolución rectoral"),
                crearCatalogo(22, "TIPO_RESOLUCION", "CONSEJO_FACULTAD", "Consejo de facultad"),
                crearCatalogo(23, "TIPO_RESOLUCION", "CONSEJO_UNIVERSITARIO", "Consejo universitario"),
                crearCatalogo(24, "TIPO_RESOLUCION", "ADMINISTRATIVA", "Resolución administrativa"),

                // ENTIDAD_SEGUIMIENTO
                crearCatalogo(30, "ENTIDAD_SEGUIMIENTO", "SOLICITUD", "Seguimiento de solicitud"),
                crearCatalogo(31, "ENTIDAD_SEGUIMIENTO", "EXPEDIENTE", "Seguimiento de expediente"),
                crearCatalogo(32, "ENTIDAD_SEGUIMIENTO", "RESOLUCION", "Seguimiento de resolución"),
                crearCatalogo(33, "ENTIDAD_SEGUIMIENTO", "ARCHIVO", "Archivo del proceso"),
                crearCatalogo(34, "ENTIDAD_SEGUIMIENTO", "NOTIFICACION", "Notificación")
            );

            catalogoRepository.saveAll(catalogos);
        };
    }

    // SOLICITUD → EXPEDIENTE → RESOLUCIÓN
    @Bean
    @Order(2)
    @Transactional
    CommandLineRunner cargarDatos(
            SolicitudRepository solicitudRepository,
            ExpedienteRepository expedienteRepository,
            ResolucionRepository resolucionRepository,
            ResolucionArticuloRepository articuloRepository,
            SeguimientoRepository seguimientoRepository,
            AuditoriaRepository auditoriaRepository
    ) {
        return args -> {

            if (solicitudRepository.count() > 0) return;

            
            // SOLICITUDES (20)
            List<SolicitudModel> solicitudes = new ArrayList<>();

            for (int i = 1; i <= 20; i++) {
                SolicitudModel s = new SolicitudModel();
                s.setCodigoSolicitud("SOL-2024-" + i);
                s.setIdPersona(1000 + i);
                s.setIdTipoSolicitud(i % 2 == 0 ? 10 : 11);
                s.setIdEstado(1); // REGISTRADO
                s.setAsunto("Solicitud administrativa N° " + i);
                s.setDetalle("Detalle de la solicitud administrativa " + i);
                s.setFechaSolicitud(LocalDate.now().minusDays(i));
                solicitudes.add(s);
            }
            solicitudRepository.saveAll(solicitudes);

            
            // EXPEDIENTES (20)
            List<ExpedienteModel> expedientes = new ArrayList<>();

            for (SolicitudModel s : solicitudes) {
                ExpedienteModel e = new ExpedienteModel();
                e.setCodigoExpediente("EXP-2024-" + s.getIdSolicitud());
                e.setIdPersona(s.getIdPersona());
                e.setIdEstado(2); // EN_PROCESO
                e.setDescripcion("Expediente generado a partir de la solicitud");
                e.setFechaApertura(LocalDate.now());
                e.setIdSolicitudOrigen(s.getIdSolicitud());
                expedientes.add(e);
            }
            expedienteRepository.saveAll(expedientes);

           
            // RESOLUCIONES (20)
            List<ResolucionModel> resoluciones = new ArrayList<>();

            for (ExpedienteModel e : expedientes) {
                ResolucionModel r = new ResolucionModel();
                r.setNumeroResolucion("RES-2024-" + e.getIdExpediente());
                r.setIdEstado(3); // APROBADO
                r.setIdTipoResolucion(22); // CONSEJO_FACULTAD
                r.setFechaEmision(LocalDate.now());
                r.setResumen("Resolución aprobatoria");
                r.setFundamento("Fundamento legal y académico");
                r.setArticuladoGeneral("Articulado general de la resolución");
                r.setAprobadoEnSesion(1);
                r.setExpediente(e);
                resoluciones.add(r);
            }
            resolucionRepository.saveAll(resoluciones);

            
            // ARTÍCULOS (40)
            List<ResolucionArticuloModel> articulos = new ArrayList<>();

            for (ResolucionModel r : resoluciones) {
                for (int i = 1; i <= 2; i++) {
                    ResolucionArticuloModel a = new ResolucionArticuloModel();
                    a.setNumeroArticulo(i);
                    a.setTitulo("Artículo " + i);
                    a.setContenido("Contenido del artículo " + i);
                    a.setResolucion(r);
                    articulos.add(a);
                }
            }
            articuloRepository.saveAll(articulos);

            // SEGUIMIENTO (60)
            List<Seguimiento> seguimientos = new ArrayList<>();

            for (SolicitudModel s : solicitudes) {
                seguimientos.add(crearSeguimiento(30, s.getIdSolicitud(), 1, "Solicitud registrada"));
            }
            for (ExpedienteModel e : expedientes) {
                seguimientos.add(crearSeguimiento(31, e.getIdExpediente(), 2, "Expediente en proceso"));
            }
            for (ResolucionModel r : resoluciones) {
                seguimientos.add(crearSeguimiento(32, r.getIdResolucion(), 3, "Resolución aprobada"));
            }
            seguimientoRepository.saveAll(seguimientos);

            // AUDITORÍA
            List<Auditoria> auditorias = new ArrayList<>();

            seguimientos.forEach(seg -> {
                Auditoria au = new Auditoria();
                au.setNombreEntidad("SEGUIMIENTO");
                au.setEntidadId(seg.getIdSeguimiento());
                au.setAccion("CREATE");
                au.setUsuarioId(10);
                au.setDatosDespues("Seguimiento creado");
                au.setObservacion("Carga inicial");
                auditorias.add(au);
            });
            auditoriaRepository.saveAll(auditorias);
        };
    }

    // MÉTODOS AUXILIARES
    private Catalogo crearCatalogo(Integer id, String categoria, String valor, String descripcion) {
        Catalogo c = new Catalogo();
        c.setIdCatalogo(id);
        c.setCategoria(categoria);
        c.setValor(valor);
        c.setDescripcion(descripcion);
        return c;
    }

    private Seguimiento crearSeguimiento(Integer entidadCatalogoId, Integer entidadId, Integer estado, String comentario) {
        Seguimiento s = new Seguimiento();
        s.setEntidadCatalogoId(entidadCatalogoId);
        s.setEntidadId(entidadId);
        s.setIdEstado(estado);
        s.setIdUsuario(10);
        s.setComentario(comentario);
        return s;
    }
}
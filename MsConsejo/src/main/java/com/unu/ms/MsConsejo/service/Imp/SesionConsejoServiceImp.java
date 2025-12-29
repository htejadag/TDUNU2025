package com.unu.ms.MsConsejo.service.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.SesionConsejoMapper;
import com.unu.ms.MsConsejo.model.request.SesionConsejoRequest;
import com.unu.ms.MsConsejo.model.response.SesionConsejoResponse;
import com.unu.ms.MsConsejo.model.response.SesionReportePeriodoResponse;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.SesionConsejoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class SesionConsejoServiceImp implements SesionConsejoService {

        private final SesionConsejoRepository sesionConsejoRepository;
        private final SesionConsejoMapper mapper;
        
        // IDs de estados de sesión (basados en catálogo)
        private static final Integer ESTADO_PROGRAMADA = 1;
        private static final Integer ESTADO_FINALIZADA = 2;
        private static final Integer ESTADO_CANCELADA = 3;

        @Override
        public List<SesionConsejoResponse> listar() {
                return sesionConsejoRepository.findAll().stream().map(mapper::toResponse).toList();
        }

        @Override
        public SesionConsejoResponse obtenerPorId(Integer id) {
                return sesionConsejoRepository.findById(id).map(mapper::toResponse).orElse(null);
        }

        @Override
        public SesionConsejoResponse guardar(SesionConsejoRequest sesionConsejoRequest) {
                SesionConsejoModel model = mapper.toEntity(sesionConsejoRequest);
                return mapper.toResponse(sesionConsejoRepository.save(model));
        }

        @Override
        public void eliminar(Integer id) {
                sesionConsejoRepository.deleteById(id);
        }

        @Override
        public SesionConsejoResponse actualizar(Integer id, SesionConsejoRequest sesionConsejoActualizado) {
                SesionConsejoModel model = sesionConsejoRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Sesion del Consejo no encontrado con id: " + id));
                mapper.updateEntityFromRequest(sesionConsejoActualizado, model);
                return mapper.toResponse(sesionConsejoRepository.save(model));
        }

        @Override
        public boolean existePorId(Integer id) {
                return sesionConsejoRepository.existsById(id);
        }

        @Override
        public List<SesionConsejoResponse> buscarPorConsejo(Integer idConsejo) {
                return sesionConsejoRepository.findByConsejo_IdConsejo(idConsejo).stream().map(mapper::toResponse).toList();
        }

        @Override
        public SesionConsejoResponse buscarPorNumeroSesion(String numeroSesion) {
                return sesionConsejoRepository.findByNumeroSesion(numeroSesion).map(mapper::toResponse).orElse(null);
        }

        @Override
        public List<SesionConsejoResponse> buscarPorFecha(LocalDate fechaSesion) {
                return sesionConsejoRepository.findByFechaSesion(fechaSesion).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<SesionConsejoResponse> buscarPorTipoSesion(Integer idTipoSesion) {
                return sesionConsejoRepository.findByIdTipoSesion(idTipoSesion).stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<SesionConsejoResponse> buscarPorConsejoYTipo(Integer idConsejo, Integer idTipoSesion) {
                return sesionConsejoRepository.findByConsejo_IdConsejoAndIdTipoSesion(idConsejo, idTipoSesion)
                                .stream().map(mapper::toResponse).toList();
        }

        @Override
        public List<SesionConsejoResponse> buscarPendientesPorConsejo(Integer idConsejo) {
                LocalDate hoy = LocalDate.now();
                return sesionConsejoRepository.findByConsejo_IdConsejoAndFechaSesionGreaterThanEqualAndIdEstado(
                                idConsejo, hoy, ESTADO_PROGRAMADA)
                                .stream().map(mapper::toResponse).toList();
        }

        @Override
        public SesionConsejoResponse finalizarSesion(Integer idSesion) {
                SesionConsejoModel model = sesionConsejoRepository.findById(idSesion)
                                .orElseThrow(() -> new RuntimeException("Sesión no encontrada con id: " + idSesion));
                model.setIdEstado(ESTADO_FINALIZADA);
                return mapper.toResponse(sesionConsejoRepository.save(model));
        }

        @Override
        public SesionReportePeriodoResponse obtenerReportePeriodo(LocalDate fechaInicio, LocalDate fechaFin) {
                long totalSesiones = sesionConsejoRepository.countByFechaSesionBetween(fechaInicio, fechaFin);
                long sesionesRealizadas = sesionConsejoRepository.countByFechaSesionBetweenAndIdEstado(
                                fechaInicio, fechaFin, ESTADO_FINALIZADA);
                long sesionesPendientes = sesionConsejoRepository.countByFechaSesionBetweenAndIdEstado(
                                fechaInicio, fechaFin, ESTADO_PROGRAMADA);
                long sesionesCanceladas = sesionConsejoRepository.countByFechaSesionBetweenAndIdEstado(
                                fechaInicio, fechaFin, ESTADO_CANCELADA);

                return SesionReportePeriodoResponse.builder()
                                .fechaInicio(fechaInicio)
                                .fechaFin(fechaFin)
                                .totalSesiones(totalSesiones)
                                .sesionesRealizadas(sesionesRealizadas)
                                .sesionesPendientes(sesionesPendientes)
                                .sesionesCanceladas(sesionesCanceladas)
                                .build();
        }

}
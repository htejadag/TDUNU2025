package com.unu.ms.MsConsejo.service.Imp;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.util.List;

import org.springframework.stereotype.Service;

import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.mapper.ConsejoMapper;
import com.unu.ms.MsConsejo.model.mapper.MiembroConsejoMapper;
import com.unu.ms.MsConsejo.model.request.ConsejoRequest;
import com.unu.ms.MsConsejo.model.response.ConsejoDetalleCompletoResponse;
import com.unu.ms.MsConsejo.model.response.ConsejoResponse;
import com.unu.ms.MsConsejo.model.response.MiembroConsejoResponse;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;
import com.unu.ms.MsConsejo.service.ConsejoService;

@Slf4j
@Service
@AllArgsConstructor
public class ConsejoServiceImp implements ConsejoService {

    private final ConsejoRepository consejoRepository;
    private final MiembroConsejoRepository miembroConsejoRepository;
    private final SesionConsejoRepository sesionConsejoRepository;
    private final ConsejoMapper mapper;
    private final MiembroConsejoMapper miembroMapper;

    @Override
    public List<ConsejoResponse> listar() {
            return consejoRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public ConsejoResponse obtenerPorId(Integer id) {
            return consejoRepository.findById(id).map(mapper::toResponse).orElse(null);
    }

    @Override
    public ConsejoResponse guardar(ConsejoRequest consejoRequest) {
            ConsejoModel model = mapper.toEntity(consejoRequest);
            return mapper.toResponse(consejoRepository.save(model));
    }

    @Override
    public void eliminar(Integer id) {
            consejoRepository.deleteById(id);
    }

    @Override
    public ConsejoResponse actualizar(Integer id, ConsejoRequest consejoActualizado) {
            ConsejoModel model = consejoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Consejo no encontrado con id: " + id));
            mapper.updateEntityFromRequest(consejoActualizado, model);
            return mapper.toResponse(consejoRepository.save(model));
    }

    @Override
    public boolean existePorId(Integer id) {
            return consejoRepository.existsById(id);
    }

    @Override
    public ConsejoResponse buscarPorNombre(String nombre) {
            return consejoRepository.findByNombre(nombre).map(mapper::toResponse).orElse(null);
    }

    @Override
    public List<ConsejoResponse> buscarPorEstado(Integer idEstado) {
            return consejoRepository.findByIdEstado(idEstado).stream().map(mapper::toResponse).toList();
    }

    @Override
    public List<ConsejoResponse> listarVigentes() {
            // Estado activo = 1 (puedes ajustar según tu catalogo)
            Integer estadoActivo = 1;
            return consejoRepository.findByIdEstado(estadoActivo).stream().map(mapper::toResponse).toList();
    }

    @Override
    public ConsejoDetalleCompletoResponse obtenerDetalleCompleto(Integer id) {
            ConsejoModel consejo = consejoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Consejo no encontrado con id: " + id));
            
            // Obtener miembros activos
            List<MiembroConsejoResponse> miembrosActivos = miembroConsejoRepository
                    .findActivosByConsejoId(id)
                    .stream()
                    .map(miembroMapper::toResponse)
                    .toList();
            
            // Contar sesiones realizadas
            long totalSesiones = sesionConsejoRepository.countByConsejo_IdConsejo(id);

            // Convertir fecha de creación desde auditoría a LocalDateTime
            var fechaCreacion = consejo.getAuFechacr() != null
                    ? consejo.getAuFechacr().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
                    : null;
            
            return ConsejoDetalleCompletoResponse.builder()
                    .idConsejo(consejo.getIdConsejo())
                    .nombre(consejo.getNombre())
                    .descripcion(consejo.getDescripcion())
                    .idEstado(consejo.getIdEstado())
                    .fechaCreacion(fechaCreacion)
                    .totalMiembrosActivos(miembrosActivos.size())
                    .totalSesionesRealizadas((int) totalSesiones)
                    .miembrosActivos(miembrosActivos)
                    .build();
    }

}

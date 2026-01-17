package com.example.MsPlanEstudios.service.Imp;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.example.MsPlanEstudios.model.response.MallaPlanResponse;
import com.example.MsPlanEstudios.model.response.CicloMallaResponse;
import com.example.MsPlanEstudios.model.response.CursoMallaResponse;
import com.example.MsPlanEstudios.repository.PlanEstudiosRepository;
import com.example.MsPlanEstudios.model.entity.PlanEstudiosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.message.CursoDetallePublisher;
import com.example.MsPlanEstudios.model.entity.CatalogoModel;
import com.example.MsPlanEstudios.model.entity.PlanEstudiosDetalleModel;
import com.example.MsPlanEstudios.model.entity.PlanEstudiosPrerequisitoModel;
import com.example.MsPlanEstudios.model.events.cursoDetalleEvent;
import com.example.MsPlanEstudios.model.request.PlanEstudiosDetalleRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosDetalleResponse;
import com.example.MsPlanEstudios.repository.CatalogoRepository;
import com.example.MsPlanEstudios.repository.PlanEstudiosDetalleRepository;
import com.example.MsPlanEstudios.repository.PlanEstudiosPrerequisitoRepository;
import com.example.MsPlanEstudios.service.PlanEstudiosDetalleService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanEstudiosDetalleServiceImp implements PlanEstudiosDetalleService {
    @Autowired
    PlanEstudiosDetalleRepository planestudiosdetalleRepository;

    @Autowired
    private PlanEstudiosPrerequisitoRepository prerequisitoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CatalogoRepository catalogoRepository;

    @Autowired
    private PlanEstudiosRepository planEstudiosRepository;

    @Autowired
    private CursoDetallePublisher cursoDetallePublisher;

    @Override
    public List<PlanEstudiosDetalleResponse> listar() {
        return planestudiosdetalleRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, PlanEstudiosDetalleResponse.class))
                .toList();
    }

    @Override
    public PlanEstudiosDetalleResponse obtenerPorId(Integer id) {
        return planestudiosdetalleRepository.findById(id)
                .map(model -> modelMapper.map(model, PlanEstudiosDetalleResponse.class))
                .orElse(null);
    }

    @Override
    public PlanEstudiosDetalleResponse guardar(PlanEstudiosDetalleRequest request) {
        // 1. Obtener ciclo desde catálogo
        CatalogoModel ciclo = catalogoRepository
                .findByIdAndEstadoTrue(request.getIdCiclo())
                .orElseThrow(() -> new RuntimeException("Ciclo no válido"));

        // 2️. Crear entity MANUALMENTE
        PlanEstudiosDetalleModel model = new PlanEstudiosDetalleModel();

        model.setIdPlanEstudio(request.getIdPlanEstudio());
        model.setIdCurso(request.getIdCurso());
        model.setCiclo(ciclo);
        model.setEstado(request.getEstado());
        model.setCreditos(request.getCreditos());
        model.setHorasTeoricas(request.getHorasTeoricas());
        model.setHorasPracticas(request.getHorasPracticas());
        model.setOrdenEnCiclo(request.getOrdenEnCiclo());

        // 3️. Guardar
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(model);

        // 4. Enviar a Kafka
        cursoDetalleEvent event = cursoDetalleEvent.builder()
                .idDetalleCurso(saved.getId())
                .cursoNombre(request.getCursoNombre())
                .build();
        cursoDetallePublisher.publish(event);

        // 4️. Response (AQUÍ SÍ PUEDE USARSE ModelMapper)
        return modelMapper.map(saved, PlanEstudiosDetalleResponse.class);
    }

    @Override
    public PlanEstudiosDetalleResponse modificar(Integer id, PlanEstudiosDetalleRequest request) {
        // 1. Buscar el registro existente
        PlanEstudiosDetalleModel existente = planestudiosdetalleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de plan de estudios no encontrado"));

        // 2️. Obtener ciclo desde catálogo (igual que en guardar)
        CatalogoModel ciclo = catalogoRepository
                .findByIdAndEstadoTrue(request.getIdCiclo())
                .orElseThrow(() -> new RuntimeException("Ciclo no válido"));

        // 3. Actualizar los campos manualmente (NO usar ModelMapper para evitar el
        // error de tipos)
        existente.setIdPlanEstudio(request.getIdPlanEstudio());
        existente.setIdCurso(request.getIdCurso());
        existente.setCiclo(ciclo);
        existente.setIdTipoCursoPlan(request.getIdTipoCursoPlan());
        existente.setEstado(request.getEstado());
        existente.setCreditos(request.getCreditos());
        existente.setHorasTeoricas(request.getHorasTeoricas());
        existente.setHorasPracticas(request.getHorasPracticas());
        existente.setOrdenEnCiclo(request.getOrdenEnCiclo());

        // 4️. Guardar en BD
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(existente);

        // 5. Model -> Response
        return modelMapper.map(saved, PlanEstudiosDetalleResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        planestudiosdetalleRepository.deleteById(id);
    }

    @Override
    public List<PlanEstudiosDetalleResponse> listarMallaPorPlan(Integer idPlanEstudio) {
        List<PlanEstudiosDetalleModel> detalles = planestudiosdetalleRepository
                .findByIdPlanEstudioAndEstadoTrueOrderByCicloCodigoAscOrdenEnCicloAsc(idPlanEstudio);

        return detalles.stream().map(det -> {

            List<Integer> prerequisitos = prerequisitoRepository
                    .findByIdPlanEstudioDetalleAndEstadoTrue(det.getId())
                    .stream()
                    .map(PlanEstudiosPrerequisitoModel::getIdCursoPrerequisito)
                    .toList();

            PlanEstudiosDetalleResponse resp = new PlanEstudiosDetalleResponse();

            resp.setId(det.getId());
            resp.setIdPlanEstudio(det.getIdPlanEstudio());
            resp.setIdCurso(det.getIdCurso());
            resp.setIdCiclo(det.getCiclo().getId());
            resp.setCreditos(det.getCreditos());
            resp.setHorasTeoricas(det.getHorasTeoricas());
            resp.setHorasPracticas(det.getHorasPracticas());
            resp.setOrdenEnCiclo(det.getOrdenEnCiclo());
            resp.setPrerrequisitos(prerequisitos);

            return resp;

        }).toList();
    }

    @Override
    public MallaPlanResponse obtenerMallaAnidadaPorPlan(Integer idPlanEstudio) {
        PlanEstudiosModel plan = planEstudiosRepository.findById(idPlanEstudio)
                .orElseThrow(() -> new RuntimeException("Plan de estudios no encontrado"));

        List<PlanEstudiosDetalleModel> detalles = planestudiosdetalleRepository
                .findByIdPlanEstudioAndEstadoTrueOrderByCicloCodigoAscOrdenEnCicloAsc(idPlanEstudio);

        MallaPlanResponse malla = new MallaPlanResponse();
        malla.setPlan(plan.getAño());

        // Agrupar por id de ciclo manteniendo orden (LinkedHashMap)
        Map<Integer, List<PlanEstudiosDetalleModel>> grouped = detalles.stream()
                .collect(Collectors.groupingBy(det -> det.getCiclo().getId(), LinkedHashMap::new, Collectors.toList()));

        List<CicloMallaResponse> ciclos = grouped.entrySet().stream().map(entry -> {
            CatalogoModel cicloModel = entry.getValue().get(0).getCiclo();
            CicloMallaResponse cicloResp = new CicloMallaResponse();
            cicloResp.setId(cicloModel.getId());
            cicloResp.setNombre(cicloModel.getDescripcion());

            List<CursoMallaResponse> cursos = entry.getValue().stream().map(det -> {
                CursoMallaResponse curso = new CursoMallaResponse();
                curso.setId(det.getIdCurso());
                curso.setCreditos(det.getCreditos());
                curso.setHorasTeoricas(det.getHorasTeoricas());
                curso.setHorasPracticas(det.getHorasPracticas());
                curso.setOrdenEnCiclo(det.getOrdenEnCiclo());

                List<Integer> prerequisitos = prerequisitoRepository
                        .findByIdPlanEstudioDetalleAndEstadoTrue(det.getId())
                        .stream()
                        .map(PlanEstudiosPrerequisitoModel::getIdCursoPrerequisito)
                        .toList();

                curso.setPrerrequisitos(prerequisitos);
                return curso;
            }).toList();

            cicloResp.setCursos(cursos);
            return cicloResp;
        }).toList();

        malla.setCiclos(ciclos);

        return malla;
    }

}

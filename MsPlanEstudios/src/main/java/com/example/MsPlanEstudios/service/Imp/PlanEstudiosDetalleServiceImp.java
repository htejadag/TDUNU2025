package com.example.MsPlanEstudios.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsPlanEstudios.model.entity.PlanEstudiosDetalleModel;
import com.example.MsPlanEstudios.model.entity.PlanEstudiosPrerequisitoModel;
import com.example.MsPlanEstudios.model.request.PlanEstudiosDetalleRequest;
import com.example.MsPlanEstudios.model.response.PlanEstudiosDetalleResponse;
import com.example.MsPlanEstudios.repository.PlanEstudiosDetalleRepository;
import com.example.MsPlanEstudios.repository.PlanEstudiosPrerequisitoRepository;
import com.example.MsPlanEstudios.service.CatalogoService;
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
    private CatalogoService catalogoService;

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
        catalogoService.obtenerActivo(request.getIdCiclo());
        //catalogoService.obtenerActivo(request.getIdTipoCursoPlan());
        
        // 1. Request -> Model
        PlanEstudiosDetalleModel model = modelMapper.map(request, PlanEstudiosDetalleModel.class);

        model.setId(null);

        // 2. Guardar en BD
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(model);

        // 3. Model -> Response
        PlanEstudiosDetalleResponse response = modelMapper.map(saved, PlanEstudiosDetalleResponse.class);

        return response;
    }

    @Override
    public PlanEstudiosDetalleResponse modificar(Integer id, PlanEstudiosDetalleRequest request) {
        // 1. Request -> Model
        PlanEstudiosDetalleModel model = modelMapper.map(request, PlanEstudiosDetalleModel.class);

        // 2. Asignar el id que viene por parámetro
        model.setId(id);

        // 3. Guardar en BD (si el id existe, hace UPDATE; si no, INSERT)
        PlanEstudiosDetalleModel saved = planestudiosdetalleRepository.save(model);

        // 4. Model -> Response
        PlanEstudiosDetalleResponse response = modelMapper.map(saved, PlanEstudiosDetalleResponse.class);

        return response;
    }

    @Override
    public void eliminar(Integer id) {
        planestudiosdetalleRepository.deleteById(id);
    }

    @Override
    public List<PlanEstudiosDetalleResponse> listarMallaPorPlan(Integer idPlanEstudio) {
        List<PlanEstudiosDetalleModel> detalles = planestudiosdetalleRepository
                .findByIdPlanEstudioAndEstadoTrueOrderByIdCicloAscOrdenEnCicloAsc(idPlanEstudio);

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
            resp.setIdCiclo(det.getIdCiclo());
            resp.setCreditos(det.getCreditos());
            resp.setHorasTeoricas(det.getHorasTeoricas());
            resp.setHorasPracticas(det.getHorasPracticas());
            resp.setOrdenEnCiclo(det.getOrdenEnCiclo());
            resp.setPrerrequisitos(prerequisitos);

            return resp;

        }).toList();
    }

}

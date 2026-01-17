package Ms_Reingresante.Ms_Reingresante.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import Ms_Reingresante.Ms_Reingresante.model.entity.SolicitudReingresoModel;
import Ms_Reingresante.Ms_Reingresante.model.request.SolicitudReingresoRequest;
import Ms_Reingresante.Ms_Reingresante.model.response.SolicitudReingresoResponse;
import Ms_Reingresante.Ms_Reingresante.repository.SolicitudReingresoRepository;
import Ms_Reingresante.Ms_Reingresante.service.SolicitudReingresoService;

@Service
public class SolicitudReingresoServiceImpl implements SolicitudReingresoService {

    @Autowired
    private SolicitudReingresoRepository solicitudReingresoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SolicitudReingresoResponse> listar() {
        return solicitudReingresoRepository.findAll()
                .stream()
                .map(model -> modelMapper.map(model, SolicitudReingresoResponse.class))
                .toList();
    }

    @Override
    public SolicitudReingresoResponse obtenerPorId(Integer id) {
        return solicitudReingresoRepository.findById(id)
                .map(model -> modelMapper.map(model, SolicitudReingresoResponse.class))
                .orElse(null);
    }

    @Override
public SolicitudReingresoResponse guardar(SolicitudReingresoRequest request) {

    SolicitudReingresoModel model = modelMapper.map(request, SolicitudReingresoModel.class);

    // 🔴 CLAVE: forzar INSERT
    model.setIdSolicitud(null);

    SolicitudReingresoModel saved = solicitudReingresoRepository.save(model);

    return modelMapper.map(saved, SolicitudReingresoResponse.class);
}



    @Override
    public void eliminar(Integer id) {
        solicitudReingresoRepository.deleteById(id);
    }
}
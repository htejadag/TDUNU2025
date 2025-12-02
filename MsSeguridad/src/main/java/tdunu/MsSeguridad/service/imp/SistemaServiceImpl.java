package tdunu.MsSeguridad.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsSeguridad.model.entity.SistemaModel;
import tdunu.MsSeguridad.model.request.SistemaRequest;
import tdunu.MsSeguridad.model.response.SistemaResponse;
import tdunu.MsSeguridad.repository.SistemaRepository;
import tdunu.MsSeguridad.service.SistemaService;

@Service
public class SistemaServiceImpl implements SistemaService {

    @Autowired
    private SistemaRepository repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<SistemaResponse> listar() {
        return repo.findAll().stream()
                .map(s -> mapper.map(s, SistemaResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SistemaResponse guardar(SistemaRequest request) {
        SistemaModel modelo = mapper.map(request, SistemaModel.class);
        SistemaModel guardado = repo.save(modelo);
        return mapper.map(guardado, SistemaResponse.class);
    }

    @Override
    public SistemaResponse buscar(Long id) {
        SistemaModel sistema = repo.findById(id).orElse(null);
        return mapper.map(sistema, SistemaResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

}

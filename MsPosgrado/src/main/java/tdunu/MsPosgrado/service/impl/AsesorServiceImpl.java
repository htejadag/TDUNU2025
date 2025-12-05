package tdunu.MsPosgrado.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsPosgrado.model.AsesorModel;
import tdunu.MsPosgrado.repository.AsesorRepository;
import tdunu.MsPosgrado.service.AsesorService;

@Service
public class AsesorServiceImpl implements AsesorService{

    @Autowired
    AsesorRepository asesorRepository;

    @Override
    public List<AsesorModel> listar() {
        return asesorRepository.findAll();
    }

    @Override
    public AsesorModel obtenerPorId(Integer id) {
        return asesorRepository.findById(id).orElse(null);
    }

    @Override
    public AsesorModel guardar(AsesorModel asesor) {
        return asesorRepository.save(asesor);
    }

    @Override
    public void eliminar(Integer id) {
        asesorRepository.deleteById(id);
    }
}
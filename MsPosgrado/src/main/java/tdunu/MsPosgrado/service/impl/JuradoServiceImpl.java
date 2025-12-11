package tdunu.MsPosgrado.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdunu.MsPosgrado.model.JuradoModel;
import tdunu.MsPosgrado.repository.JuradoRepository;
import tdunu.MsPosgrado.service.JuradoService;

@Service
public class JuradoServiceImpl implements JuradoService{

    @Autowired
    JuradoRepository juradoRepository;

    @Override
    public List<JuradoModel> listar() {
        return juradoRepository.findAll();
    }

    @Override
    public JuradoModel obtenerPorId(Integer id) {
        return juradoRepository.findById(id).orElse(null);
    }

    @Override
    public JuradoModel guardar(JuradoModel jurado) {
        return juradoRepository.save(jurado);
    }

    @Override
    public void eliminar(Integer id) {
        juradoRepository.deleteById(id);
    }

    
}
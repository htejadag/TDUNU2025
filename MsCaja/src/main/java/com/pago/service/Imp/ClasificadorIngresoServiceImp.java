package com.pago.service.Imp;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pago.model.entity.ClasificadorIngresoModel;
import com.pago.repository.ClasificadorIngresoRepository;
import com.pago.service.ClasificadorIngresoService;

@Service("clasificador_ingresoServicio")
public class ClasificadorIngresoServiceImp implements ClasificadorIngresoService {
    @Autowired
    @Qualifier("clasificador_ingresoRepositorio")
    private ClasificadorIngresoRepository clasificador_ingresoRepositorio;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClasificadorIngresoModel> listarClasificadorIngreso(){
        return clasificador_ingresoRepositorio.findAll().
        stream()
        .map(model -> modelMapper.map(model, ClasificadorIngresoModel.class))
        .toList();
    }

    @Override
    public ClasificadorIngresoModel obtenerClasificadorIngreso(int id){
        return clasificador_ingresoRepositorio.findById(id)
        .map(model -> modelMapper.map(model, ClasificadorIngresoModel.class))
        .orElse(null);
    }

    @Override
    public ClasificadorIngresoModel registrarClasificadorIngreso(ClasificadorIngresoModel clasificador){
        ClasificadorIngresoModel model = modelMapper.map(clasificador, ClasificadorIngresoModel.class);
        ClasificadorIngresoModel saved = clasificador_ingresoRepositorio.save(model);
        return modelMapper.map(saved, ClasificadorIngresoModel.class);
    }

    @Override
    public ClasificadorIngresoModel actualizarClasificadorIngreso(ClasificadorIngresoModel clasificador){
        ClasificadorIngresoModel model = modelMapper.map(clasificador, ClasificadorIngresoModel.class);
        ClasificadorIngresoModel saved = clasificador_ingresoRepositorio.save(model);
        return modelMapper.map(saved, ClasificadorIngresoModel.class);
    }

    @Override
    public void desactivarClasificadorIngreso(int id) {
        clasificador_ingresoRepositorio.desactivar(id);
    }

    @Override
    public void eliminarClasificadorIngreso(int id) {
        clasificador_ingresoRepositorio.eliminar(id);
    }

}

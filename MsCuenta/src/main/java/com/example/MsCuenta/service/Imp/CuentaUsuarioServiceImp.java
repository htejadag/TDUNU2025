package com.example.MsCuenta.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaUsuarioRequest;
import com.example.MsCuenta.model.response.CuentaUsuarioResponse;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.service.CuentaUsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaUsuarioServiceImp implements CuentaUsuarioService {


    @Autowired
    CuentaUsuarioRepository cuentaUsuarioRepository;
    

    @Autowired
    private ModelMapper modelMapper;




    @Override
    public List<CuentaUsuarioResponse> listar() {

        return cuentaUsuarioRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, CuentaUsuarioResponse.class))
            .toList();
        
    }

    @Override
    public CuentaUsuarioResponse obtenerPorId(Integer id) {
         return cuentaUsuarioRepository.findById(id)
            .map(model -> modelMapper.map(model, CuentaUsuarioResponse.class))
            .orElse(null);
    }

    @Override
    public CuentaUsuarioResponse guardar(CuentaUsuarioRequest cuentaUsuarioRequest) {
        
        CuentaUsuarioModel model = new CuentaUsuarioModel();

        model.setIdUsuarioRol(cuentaUsuarioRequest.getIdUsuarioRol());
        model.setSaldo(cuentaUsuarioRequest.getSaldo());
        model.setActivo(cuentaUsuarioRequest.isActivo());

        // Auditoría (NUNCA confiar en el request)
        model.setUsuarioCreacion(cuentaUsuarioRequest.getUsuarioCreacion());
        model.setFechaCreacion(cuentaUsuarioRequest.getFechaCreacion().toString());
        model.setUsuarioModificacion(cuentaUsuarioRequest.getUsuarioModificacion());
        model.setFechaModificacion(cuentaUsuarioRequest.getFechaModificacion().toString());


       
        CuentaUsuarioModel saved = cuentaUsuarioRepository.save(model);

       
        CuentaUsuarioResponse response = modelMapper.map(saved, CuentaUsuarioResponse.class);

        return response;
    }

    @Override
    public CuentaUsuarioResponse modificar(Integer id, CuentaUsuarioRequest cuentaUsuarioRequest) {


    CuentaUsuarioModel model = cuentaUsuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe un comedor con id: " + id));

    modelMapper.map(cuentaUsuarioRequest, model);

    CuentaUsuarioModel actualizado = cuentaUsuarioRepository.save(model);

    return modelMapper.map(actualizado, CuentaUsuarioResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        cuentaUsuarioRepository.deleteById(id);
    }


    
}

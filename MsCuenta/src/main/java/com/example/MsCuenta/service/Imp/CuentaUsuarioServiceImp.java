package com.example.MsCuenta.service.Imp;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioRequest;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioUpdateRequest;
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
    public CuentaUsuarioResponse guardar(CuentaUsuarioRequest request) {

        CuentaUsuarioModel model = new CuentaUsuarioModel();

        model.setIdUsuarioRol(request.getIdUsuarioRol());
        model.setSaldo(request.getSaldo());
        model.setActivo(request.isActivo());

    
        model.setUsuarioCreacion(request.getUsuarioCreacion());

        CuentaUsuarioModel saved = cuentaUsuarioRepository.save(model);

        return modelMapper.map(saved, CuentaUsuarioResponse.class);
    }


    @Override
    public CuentaUsuarioResponse modificar(Integer id, CuentaUsuarioUpdateRequest request) {

    CuentaUsuarioModel model = cuentaUsuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("No existe una cuenta usuario con id: " + id));

    model.setIdUsuarioRol(request.getIdUsuarioRol());
    model.setSaldo(request.getSaldo());
    model.setActivo(request.isActivo());


    CuentaUsuarioModel actualizado = cuentaUsuarioRepository.save(model);

    return modelMapper.map(actualizado, CuentaUsuarioResponse.class);
    }



    @Override
    public void eliminar(Integer id) {
        cuentaUsuarioRepository.deleteById(id);
    }


    
}

package com.example.MsCuenta.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.RecargaModel;
import com.example.MsCuenta.model.request.RecargaRequest;
import com.example.MsCuenta.model.response.RecargaResponse;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.repository.RecargaRepository;
import com.example.MsCuenta.service.RecargaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecargaServiceImp implements RecargaService {

    @Autowired
    RecargaRepository recargaRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CuentaUsuarioRepository cuentaUsuarioRepository;

    @Override
    public List<RecargaResponse> listar() {
        return recargaRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, RecargaResponse.class))
            .toList();
    }

    @Override
    public RecargaResponse obtenerPorId(Integer id) {
        return recargaRepository.findById(id)
            .map(model -> modelMapper.map(model, RecargaResponse.class))
            .orElse(null);
    }

    @Override
    public RecargaResponse guardar(RecargaRequest recargaRequest) {
            
        // CuentaUsuarioModel model1 = new CuentaUsuarioModel();
        RecargaModel model = new RecargaModel();

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaRequest.getId_cuenta_usuario()));

        model.setId_cuenta_usuario(idCuentaUsuario);
        model.setMetodo_pago(recargaRequest.getMetodo_pago());
        model.setReferencia(recargaRequest.getReferencia());
        model.setMonto(recargaRequest.getMonto());
        model.setFecha_recarga(recargaRequest.getFecha_recarga());

    
        model.setUsuarioCreacion(recargaRequest.getUsuarioCreacion());

        if (recargaRequest.getFechaCreacion() != null) {
            model.setFechaCreacion(recargaRequest.getFechaCreacion().toString());
        } else {
            model.setFechaCreacion(null);
        }


        model.setUsuarioModificacion(recargaRequest.getUsuarioModificacion());

        if (recargaRequest.getFechaModificacion() != null) {
            model.setFechaModificacion(recargaRequest.getFechaModificacion().toString());
        } else {
            model.setFechaModificacion(null);
        }

        RecargaModel saved = recargaRepository.save(model);

        return modelMapper.map(saved, RecargaResponse.class);
    }

    @Override
    public RecargaResponse modificar(Integer id, RecargaRequest recargaRequest) {
    
        RecargaModel model1 = recargaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe una recarga con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaRequest.getId_cuenta_usuario()));
        
        model1.setId_cuenta_usuario(idCuentaUsuario);
        model1.setMetodo_pago(recargaRequest.getMetodo_pago());
        model1.setReferencia(recargaRequest.getReferencia());
        model1.setMonto(recargaRequest.getMonto());
        model1.setFecha_recarga(recargaRequest.getFecha_recarga());

        model1.setUsuarioModificacion(recargaRequest.getUsuarioModificacion());
        model1.setUsuarioCreacion(recargaRequest.getUsuarioCreacion());


        if (recargaRequest.getFechaModificacion() != null) {
            model1.setFechaModificacion(recargaRequest.getFechaModificacion().toString());
        } else {
            model1.setFechaModificacion(null);
        }

        RecargaModel actualizado = recargaRepository.save(model1);

        return modelMapper.map(actualizado, RecargaResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        recargaRepository.deleteById(id);
    }
    
}

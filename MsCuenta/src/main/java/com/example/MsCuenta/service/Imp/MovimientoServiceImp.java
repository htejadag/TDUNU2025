package com.example.MsCuenta.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.MovimientoModel;
import com.example.MsCuenta.model.request.MovimientoRquest;
import com.example.MsCuenta.model.response.MovimientoResponse;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.repository.MovimientoRepository;
import com.example.MsCuenta.service.MovimientoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovimientoServiceImp implements MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CuentaUsuarioRepository cuentaUsuarioRepository;
    @Autowired

    
    
    @Override
    public List<MovimientoResponse> listar() {
        return movimientoRepository.findAll()
            .stream()
            .map(model -> modelMapper.map(model, MovimientoResponse.class))
            .toList();
    }

    @Override
    public MovimientoResponse obtenerPorId(Integer id) {
        return movimientoRepository.findById(id)
            .map(model -> modelMapper.map(model, MovimientoResponse.class))
            .orElse(null);
    }

    @Override
    public MovimientoResponse guardar(MovimientoRquest movimientoRequest) {

        MovimientoModel model = new MovimientoModel();

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(movimientoRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + movimientoRequest.getId_cuenta_usuario()));

        model.setId_cuenta_usuario(idCuentaUsuario.getId());
        model.setId_tipo_movimiento(movimientoRequest.getId_tipo_movimiento());
        model.setId_operacion(movimientoRequest.getId_operacion());
        model.setMonto(movimientoRequest.getMonto());
        model.setFecha_movimiento(movimientoRequest.getFecha_movimiento());
    
        model.setUsuarioCreacion(movimientoRequest.getUsuarioCreacion());

        if (movimientoRequest.getFechaCreacion() != null) {
            model.setFechaCreacion(movimientoRequest.getFechaCreacion().toString());
        } else {
            model.setFechaCreacion(null);
        }


        model.setUsuarioModificacion(movimientoRequest.getUsuarioModificacion());

        if (movimientoRequest.getFechaModificacion() != null) {
            model.setFechaModificacion(movimientoRequest.getFechaModificacion().toString());
        } else {
            model.setFechaModificacion(null);
        }

        MovimientoModel saved = movimientoRepository.save(model);

        return modelMapper.map(saved, MovimientoResponse.class);
    }

    @Override
    public MovimientoResponse modificar(Integer id, MovimientoRquest movimientoRequest) {

        MovimientoModel model1 = movimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe un movimiento con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(movimientoRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + movimientoRequest.getId_cuenta_usuario()));
        
        model1.setId_cuenta_usuario(idCuentaUsuario.getId());
        model1.setId_tipo_movimiento(movimientoRequest.getId_tipo_movimiento());
        model1.setId_operacion(movimientoRequest.getId_operacion());
        model1.setMonto(movimientoRequest.getMonto());
        model1.setFecha_movimiento(movimientoRequest.getFecha_movimiento());

        model1.setUsuarioModificacion(movimientoRequest.getUsuarioModificacion());

        if (movimientoRequest.getFechaModificacion() != null) {
            model1.setFechaModificacion(movimientoRequest.getFechaModificacion().toString());
        } else {
            model1.setFechaModificacion(null);
        }

        MovimientoModel actualizado = movimientoRepository.save(model1);

        return modelMapper.map(actualizado, MovimientoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        movimientoRepository.deleteById(id);
    }
    
}

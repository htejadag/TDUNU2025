package com.example.MsCuenta.service.Imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.MovimientoModel;
import com.example.MsCuenta.model.request.Movimiento.MovimientoRquest;
import com.example.MsCuenta.model.request.Movimiento.MovimientoUpdateRequest;
import com.example.MsCuenta.model.response.MovimientoResponse;
import com.example.MsCuenta.repository.CatalogoRepository;
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
    CatalogoRepository catalogoRepository;

    
    
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

        CatalogoModel idTipoMovimiento = catalogoRepository.findById(movimientoRequest.getId_tipo_movimiento())
        .orElseThrow(() -> new RuntimeException("No existe un tipo de movimiento con id: " + movimientoRequest.getId_tipo_movimiento()));

        model.setId_cuenta_usuario(idCuentaUsuario);
        model.setId_tipo_movimiento(idTipoMovimiento);
        model.setId_operacion(movimientoRequest.getId_operacion());
        model.setMonto(movimientoRequest.getMonto());
        model.setFecha_movimiento(movimientoRequest.getFecha_movimiento());
    
        model.setUsuarioCreacion(movimientoRequest.getUsuarioCreacion());


        MovimientoModel saved = movimientoRepository.save(model);

        return modelMapper.map(saved, MovimientoResponse.class);
    }

    @Override
    public MovimientoResponse modificar(Integer id, MovimientoUpdateRequest movimientoUpdateRequest) {

        MovimientoModel model1 = movimientoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe un movimiento con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(movimientoUpdateRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + movimientoUpdateRequest.getId_cuenta_usuario()));

        CatalogoModel idTipoMovimiento = catalogoRepository.findById(movimientoUpdateRequest.getId_tipo_movimiento())
        .orElseThrow(() -> new RuntimeException("No existe un tipo de movimiento con id: " + movimientoUpdateRequest.getId_tipo_movimiento()));
        
        model1.setId_cuenta_usuario(idCuentaUsuario);
        model1.setId_tipo_movimiento(idTipoMovimiento);
        model1.setId_operacion(movimientoUpdateRequest.getId_operacion());
        model1.setMonto(movimientoUpdateRequest.getMonto());
        model1.setFecha_movimiento(movimientoUpdateRequest.getFecha_movimiento());

        model1.setUsuarioModificacion(movimientoUpdateRequest.getUsuarioModificacion());

        MovimientoModel actualizado = movimientoRepository.save(model1);

        return modelMapper.map(actualizado, MovimientoResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        movimientoRepository.deleteById(id);
    }
    
}

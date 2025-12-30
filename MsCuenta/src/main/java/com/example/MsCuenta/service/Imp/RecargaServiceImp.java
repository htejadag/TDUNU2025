package com.example.MsCuenta.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.RecargaModel;
import com.example.MsCuenta.model.request.Recarga.RecargaRequest;
import com.example.MsCuenta.model.request.Recarga.RecargaUpdateRequest;
import com.example.MsCuenta.model.response.RecargaResponse;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.repository.RecargaRepository;
import com.example.MsCuenta.service.MovimientoService;
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
    @Autowired
    MovimientoService movimientoService;

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
            
        RecargaModel model = new RecargaModel();

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaRequest.getId_cuenta_usuario()));

        model.setIdCuentaUsuario(idCuentaUsuario);
        model.setMetodoPago(recargaRequest.getMetodo_pago());
        model.setReferencia(recargaRequest.getReferencia());
        model.setMonto(recargaRequest.getMonto());
        model.setFechaRecarga(LocalDate.now());
        model.setActivo(recargaRequest.isActivo());
        model.setUsuarioCreacion(recargaRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());

        RecargaModel saved = recargaRepository.save(model);

        movimientoService.guardar(idCuentaUsuario.getId(), recargaRequest.getMonto(), recargaRequest.getUsuarioCreacion());

        return modelMapper.map(saved, RecargaResponse.class);
    }

    @Override
    public RecargaResponse modificar(Integer id, RecargaUpdateRequest recargaUpdateRequest) {
    
        RecargaModel model = recargaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe una recarga con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaUpdateRequest.getId_cuenta_usuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaUpdateRequest.getId_cuenta_usuario()));
        
        model.setIdCuentaUsuario(idCuentaUsuario);
        model.setMetodoPago(recargaUpdateRequest.getMetodo_pago());
        model.setReferencia(recargaUpdateRequest.getReferencia());
        model.setMonto(recargaUpdateRequest.getMonto());
        model.setActivo(recargaUpdateRequest.isActivo());
        model.setUsuarioModificacion(recargaUpdateRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());


        RecargaModel actualizado = recargaRepository.save(model);

        return modelMapper.map(actualizado, RecargaResponse.class);
    }

    @Override
    public RecargaResponse eliminar(Integer id) {

        RecargaModel model = recargaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe una recarga con id: " + id));

        model.setActivo(false);

        RecargaModel actualizado = recargaRepository.save(model);

        return modelMapper.map(actualizado, RecargaResponse.class);

        
    }
    
}

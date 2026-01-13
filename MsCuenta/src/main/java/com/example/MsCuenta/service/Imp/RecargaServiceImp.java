package com.example.MsCuenta.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.Util.CatalogoEnum;
import com.example.MsCuenta.config.BusinessException;
import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.RecargaModel;
import com.example.MsCuenta.model.request.Recarga.RecargaRequest;
import com.example.MsCuenta.model.request.Recarga.RecargaUpdateRequest;
import com.example.MsCuenta.model.response.RecargaResponse;
import com.example.MsCuenta.repository.CatalogoRepository;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.repository.RecargaRepository;
import com.example.MsCuenta.service.CuentaUsuarioService;
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
    @Autowired
    CuentaUsuarioService cuentaUsuarioService;
    @Autowired
    CatalogoRepository catalogoRepository;

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

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaRequest.getIdCuentaUsuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaRequest.getIdCuentaUsuario()));

        Integer idMetodoPagoReq = recargaRequest.getIdMetodoPago();

        if (
            !idMetodoPagoReq.equals(CatalogoEnum.EFECTIVO.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.TARJETA.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.YAPE.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.PLIN.getId())
        ) {
            throw new BusinessException("Método de pago no válido");
        }


        CatalogoModel idMetodoPago = catalogoRepository.findById(idMetodoPagoReq)
        .orElseThrow(() -> new RuntimeException("No existe el metodo de pago"));

        model.setIdCuentaUsuario(idCuentaUsuario);
        model.setIdMetodoPago(idMetodoPago);
        model.setMonto(recargaRequest.getMonto());
        model.setFechaRecarga(LocalDate.now());
        model.setActivo(recargaRequest.isActivo());
        model.setUsuarioCreacion(recargaRequest.getUsuarioCreacion());
        model.setFechaCreacion(LocalDate.now());

        RecargaModel saved = recargaRepository.save(model);

        movimientoService.guardar(idCuentaUsuario.getId(), recargaRequest.getMonto(), recargaRequest.getUsuarioCreacion());

        cuentaUsuarioService.actualizarSaldo(idCuentaUsuario.getId(), recargaRequest.getMonto());

      
        return modelMapper.map(saved, RecargaResponse.class);
    }

    @Override
    public RecargaResponse modificar(Integer id, RecargaUpdateRequest recargaUpdateRequest) {
    
        RecargaModel model = recargaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No existe una recarga con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository.findById(recargaUpdateRequest.getIdCuentaUsuario())
        .orElseThrow(() -> new RuntimeException("No existe una cuenta de usuario con id: " + recargaUpdateRequest.getIdCuentaUsuario()));

        Integer idMetodoPagoReq = recargaUpdateRequest.getIdMetodoPago();
        
        if (
            !idMetodoPagoReq.equals(CatalogoEnum.EFECTIVO.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.TARJETA.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.YAPE.getId()) &&
            !idMetodoPagoReq.equals(CatalogoEnum.PLIN.getId())
        ) {
            throw new BusinessException("Método de pago no válido");
        }

         CatalogoModel idMetodoPago = catalogoRepository.findById(idMetodoPagoReq)
        .orElseThrow(() -> new RuntimeException("No existe el metodo de pago"));
        
        model.setIdCuentaUsuario(idCuentaUsuario);
        model.setIdMetodoPago(idMetodoPago);
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

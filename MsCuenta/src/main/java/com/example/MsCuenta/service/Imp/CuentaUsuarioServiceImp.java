package com.example.MsCuenta.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.MsCuenta.config.BusinessException;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioRequest;
import com.example.MsCuenta.model.request.CuentaUsuario.CuentaUsuarioUpdateRequest;
import com.example.MsCuenta.model.response.CuentaUsuarioResponse;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import com.example.MsCuenta.service.CuentaUsuarioService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CuentaUsuarioServiceImp implements CuentaUsuarioService {

    private final CuentaUsuarioRepository cuentaUsuarioRepository;

    private final ModelMapper modelMapper;

    public CuentaUsuarioServiceImp(CuentaUsuarioRepository cuentaUsuarioRepository, ModelMapper modelMapper) {

        this.cuentaUsuarioRepository = cuentaUsuarioRepository;
        this.modelMapper = modelMapper;
    }

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
        model.setFechaCreacion(LocalDate.now());

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
        model.setUsuarioModificacion(request.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        CuentaUsuarioModel actualizado = cuentaUsuarioRepository.save(model);

        return modelMapper.map(actualizado, CuentaUsuarioResponse.class);
    }

    @Override
    public CuentaUsuarioResponse eliminar(Integer id) {

        CuentaUsuarioModel model = cuentaUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe una cuenta usuario con id: " + id));

        model.setActivo(false);

        CuentaUsuarioModel actualizado = cuentaUsuarioRepository.save(model);

        return modelMapper.map(actualizado, CuentaUsuarioResponse.class);

    }

    @Override
    @Transactional
    public void descontarSaldo(Integer id) {

        CuentaUsuarioModel cuenta = cuentaUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        if (!cuenta.isActivo()) {

            throw new BusinessException("Cuenta inactiva");

        }

        if (cuenta.getSaldo() <= 0) {

            throw new BusinessException("Saldo insuficiente");
        }

        cuenta.setSaldo(cuenta.getSaldo() - 1);

        cuentaUsuarioRepository.save(cuenta);

    }

    @Override
    @Transactional
    public void actualizarSaldo(Integer id, double nuevoSaldo) {

        CuentaUsuarioModel cuenta = cuentaUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no existe"));

        if (!cuenta.isActivo()) {

            throw new BusinessException("Cuenta inactiva");

        }

        double saldoActual = cuenta.getSaldo() + nuevoSaldo;

        cuenta.setSaldo(saldoActual);

    }

}

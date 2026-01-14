package com.example.MsCuenta.service.Imp;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.example.MsCuenta.Util.CatalogoEnum;
import com.example.MsCuenta.model.entity.CatalogoModel;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.entity.MovimientoModel;
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

    private final MovimientoRepository movimientoRepository;

    private final ModelMapper modelMapper;

    private final CuentaUsuarioRepository cuentaUsuarioRepository;

    private final CatalogoRepository catalogoRepository;

    public MovimientoServiceImp(MovimientoRepository movimientoRepository, ModelMapper modelMapper,
            CuentaUsuarioRepository cuentaUsuarioRepository, CatalogoRepository catalogoRepository) {

        this.movimientoRepository = movimientoRepository;
        this.modelMapper = modelMapper;
        this.cuentaUsuarioRepository = cuentaUsuarioRepository;
        this.catalogoRepository = catalogoRepository;
    }

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
    public MovimientoResponse guardarConKafka(Integer idCuentaUsuario, Integer idUsuarioCreacion) {

        MovimientoModel model = new MovimientoModel();

        CuentaUsuarioModel cuenta = cuentaUsuarioRepository.findById(idCuentaUsuario)
                .orElseThrow(
                        () -> new RuntimeException("No existe una cuenta de usuario con ese id:" + idCuentaUsuario));

        CatalogoModel idTipoMovimiento = catalogoRepository.findById(CatalogoEnum.CONSUMO.getId())
                .orElseThrow(() -> new RuntimeException("No existe el tipo de movimiento con ese id"));

        model.setIdCuentaUsuario(cuenta);
        model.setIdTipoMovimiento(idTipoMovimiento);
        model.setMonto(1);
        model.setFechaMovimiento(LocalDate.now());
        model.setActivo(false);
        model.setUsuarioCreacion(idUsuarioCreacion);
        model.setFechaCreacion(LocalDate.now());

        MovimientoModel saved = movimientoRepository.save(model);

        return modelMapper.map(saved, MovimientoResponse.class);
    }

    @Override
    public MovimientoResponse modificar(Integer id, MovimientoUpdateRequest movimientoUpdateRequest) {

        MovimientoModel model = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un movimiento con id: " + id));

        CuentaUsuarioModel idCuentaUsuario = cuentaUsuarioRepository
                .findById(movimientoUpdateRequest.getIdCuentaUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "No existe una cuenta de usuario con id: " + movimientoUpdateRequest.getIdCuentaUsuario()));

        CatalogoModel idTipoMovimiento = catalogoRepository.findById(movimientoUpdateRequest.getIdTipoMovimiento())
                .orElseThrow(() -> new RuntimeException(
                        "No existe un tipo de movimiento con id: " + movimientoUpdateRequest.getIdTipoMovimiento()));

        model.setIdCuentaUsuario(idCuentaUsuario);
        model.setIdTipoMovimiento(idTipoMovimiento);
        model.setMonto(movimientoUpdateRequest.getMonto());
        model.setActivo(movimientoUpdateRequest.isActivo());
        model.setUsuarioModificacion(movimientoUpdateRequest.getUsuarioModificacion());
        model.setFechaModificacion(LocalDate.now());

        MovimientoModel actualizado = movimientoRepository.save(model);

        return modelMapper.map(actualizado, MovimientoResponse.class);
    }

    @Override
    public MovimientoResponse eliminar(Integer id) {
        MovimientoModel model = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe un movimiento con id: " + id));

        model.setActivo(false);

        MovimientoModel actualizado = movimientoRepository.save(model);

        return modelMapper.map(actualizado, MovimientoResponse.class);

    }

    @Override
    public MovimientoResponse guardar(Integer idCuentaUsuario, double monto, Integer idUsuarioCreacion) {

        CuentaUsuarioModel cuenta = cuentaUsuarioRepository.findById(idCuentaUsuario)
                .orElseThrow(() -> new RuntimeException("No existe la cuenta"));

        CatalogoModel tipoMovimiento = catalogoRepository.findById(CatalogoEnum.RECARGA.getId())
                .orElseThrow(() -> new RuntimeException("Tipo movimiento no existe"));

        MovimientoModel model = new MovimientoModel();
        model.setIdCuentaUsuario(cuenta);
        model.setIdTipoMovimiento(tipoMovimiento);
        model.setMonto(monto);
        model.setFechaMovimiento(LocalDate.now());
        model.setActivo(true);
        model.setUsuarioCreacion(idUsuarioCreacion);
        model.setFechaCreacion(LocalDate.now());

        MovimientoModel saved = movimientoRepository.save(model);
        return modelMapper.map(saved, MovimientoResponse.class);

    }

    @Override
    public List<MovimientoResponse> listarMovimientoPorUsuario(Integer idCuentaUsuario) {

        cuentaUsuarioRepository.findById(idCuentaUsuario)
                .orElseThrow(() -> new RuntimeException(
                        "No existe una cuenta de usuario con id: " + idCuentaUsuario));

        return movimientoRepository
                .findByIdCuentaUsuarioId(idCuentaUsuario)
                .stream()
                .map(model -> modelMapper.map(model, MovimientoResponse.class))
                .toList();

    }

}

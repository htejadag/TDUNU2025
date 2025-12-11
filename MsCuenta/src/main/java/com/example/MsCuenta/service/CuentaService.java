package com.example.MsCuenta.service;


import com.example.MsCuenta.model.entity.AuditoriaModel;
import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaRequest;
import com.example.MsCuenta.repository.AuditoriaRepository;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaUsuarioRepository cuentaRepository;

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    // Formato de fecha para respetar tu String en la base de datos
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // --- CRUD CUENTA ---

    public List<CuentaUsuarioModel> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<CuentaUsuarioModel> obtenerPorId(Integer id) {
        return cuentaRepository.findById(id);
    }

    public CuentaUsuarioModel crearCuenta(CuentaRequest request, Integer idUsuarioOperador) {
        CuentaUsuarioModel cuenta = new CuentaUsuarioModel();
        
        // Mapeo de datos
        cuenta.setIdUsuarioRol(request.getIdUsuarioRol());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setActivo(request.isActivo());

        // Auditoría Interna (Campos de tu tabla)
        String fechaActual = LocalDateTime.now().format(FORMATTER);
        cuenta.setUsuarioCreacion(idUsuarioOperador);
        cuenta.setFechaCreacion(fechaActual);
        
        // Guardar
        CuentaUsuarioModel cuentaGuardada = cuentaRepository.save(cuenta);

        // Registrar en Auditoría Externa
        registrarAuditoria("CuentaUsuario", cuentaGuardada.getId(), "CREAR", idUsuarioOperador, "Saldo inicial: " + request.getSaldo());

        return cuentaGuardada;
    }

    public CuentaUsuarioModel actualizarCuenta(Integer id, CuentaRequest request, Integer idUsuarioOperador) {
        return cuentaRepository.findById(id).map(cuenta -> {
            // Actualizar datos
            cuenta.setIdUsuarioRol(request.getIdUsuarioRol());
            cuenta.setSaldo(request.getSaldo());
            cuenta.setActivo(request.isActivo());

            // Auditoría Interna
            String fechaActual = LocalDateTime.now().format(FORMATTER);
            cuenta.setUsuarioModificacion(idUsuarioOperador);
            cuenta.setFechaModificacion(fechaActual);

            CuentaUsuarioModel cuentaActualizada = cuentaRepository.save(cuenta);

            // Registrar en Auditoría Externa
            registrarAuditoria("CuentaUsuario", cuentaActualizada.getId(), "ACTUALIZAR", idUsuarioOperador, "Actualización de saldo/estado");

            return cuentaActualizada;
        }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public void eliminarCuenta(Integer id, Integer idUsuarioOperador) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
            // Registrar en Auditoría Externa
            registrarAuditoria("CuentaUsuario", id, "ELIMINAR", idUsuarioOperador, "Cuenta eliminada permanentemente");
        } else {
            throw new RuntimeException("Cuenta no encontrada para eliminar");
        }
    }

    // --- CRUD AUDITORIA (SOLO LECTURA) ---
    
    public List<AuditoriaModel> listarAuditoria() {
        return auditoriaRepository.findAll();
    }

    // Método privado auxiliar para guardar el log
    private void registrarAuditoria(String entidad, Integer idRegistro, String accion, Integer idUsuario, String detalles) {
        AuditoriaModel log = new AuditoriaModel();
        log.setEntidad(entidad);
        log.setIdRegistro(idRegistro);
        log.setAccion(accion);
        log.setIdUsuario(idUsuario);
        log.setFecha(LocalDateTime.now());
        log.setDetalles(detalles);
        auditoriaRepository.save(log);
    }
}
package com.example.MsCuenta.service;

import com.example.MsCuenta.model.entity.CuentaUsuarioModel;
import com.example.MsCuenta.model.request.CuentaRequest;
import com.example.MsCuenta.repository.CuentaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {

    @Autowired
    private CuentaUsuarioRepository cuentaRepository;

    // @Autowired
    // private AuditoriaRepository auditoriaRepository; // ELIMINADO: Ya no existe tabla independiente

    // --- CRUD CUENTA ---

    public List<CuentaUsuarioModel> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<CuentaUsuarioModel> obtenerPorId(Integer id) {
        return cuentaRepository.findById(id);
    }

    public CuentaUsuarioModel crearCuenta(CuentaRequest request, Integer idUsuarioOperador) {
        CuentaUsuarioModel cuenta = new CuentaUsuarioModel();
        
        // Mapeo de datos funcionales
        cuenta.setIdUsuarioRol(request.getIdUsuarioRol());
        cuenta.setSaldo(request.getSaldo());
        cuenta.setActivo(request.isActivo());

        // --- AUDITORÍA (Ahora se guarda DIRECTO en la tabla cuenta_usuario) ---
        cuenta.setEntidad("CuentaUsuario");
        cuenta.setAccion("CREAR");
        cuenta.setIdUsuario(idUsuarioOperador);
        cuenta.setFecha(LocalDateTime.now());
        cuenta.setDetalles("Saldo inicial: " + request.getSaldo());

        // El idRegistro será el mismo ID de la cuenta, pero al crear es null hasta que se guarde.
        // Si es vital tenerlo, podrías hacer un flush, pero para simplificar lo dejamos así o lo llenamos luego.
        
        return cuentaRepository.save(cuenta);
    }

    public CuentaUsuarioModel actualizarCuenta(Integer id, CuentaRequest request, Integer idUsuarioOperador) {
        return cuentaRepository.findById(id).map(cuenta -> {
            // Actualizar datos funcionales
            cuenta.setIdUsuarioRol(request.getIdUsuarioRol());
            cuenta.setSaldo(request.getSaldo());
            cuenta.setActivo(request.isActivo());

            // --- AUDITORÍA (Actualizamos los campos heredados) ---
            cuenta.setEntidad("CuentaUsuario");
            cuenta.setAccion("ACTUALIZAR");
            cuenta.setIdUsuario(idUsuarioOperador);
            cuenta.setFecha(LocalDateTime.now());
            cuenta.setDetalles("Actualización de saldo/estado");
            cuenta.setIdRegistro(id); // Ahora sí tenemos el ID

            return cuentaRepository.save(cuenta);
        }).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    public void eliminarCuenta(Integer id, Integer idUsuarioOperador) {
        if (cuentaRepository.existsById(id)) {
            // Al borrar la cuenta, se borra su auditoría interna (porque es la misma fila).
            // Si quisieras mantener historial, deberías usar "Borrado Lógico" (campo activo = false)
            // en lugar de deleteById. Por ahora, usamos delete físico:
            cuentaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cuenta no encontrada para eliminar");
        }
    }

    // --- NOTA: Los métodos de listarAuditoria se eliminan porque ya no hay tabla separada de logs ---
}
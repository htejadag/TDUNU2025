package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;
import com.unu.ms.MsSecretariaAcademica.repository.AuditoriaRepository;

@Slf4j
@Service
@AllArgsConstructor
public class AuditoriaServiceImp {

    AuditoriaRepository auditoriaRepository;

    public void registrarAccion(String nombreEntidad, Integer entidadId, String accion,
                                Integer usuarioId, String datosAntes, String datosDespues,
                                String observacion) {
        Auditoria auditoria = new Auditoria();
        auditoria.setNombreEntidad(nombreEntidad);
        auditoria.setEntidadId(entidadId);
        auditoria.setAccion(accion);
        auditoria.setUsuarioId(usuarioId);
        auditoria.setDatosAntes(datosAntes);
        auditoria.setDatosDespues(datosDespues);
        auditoria.setObservacion(observacion);
        auditoriaRepository.save(auditoria);
    }

}

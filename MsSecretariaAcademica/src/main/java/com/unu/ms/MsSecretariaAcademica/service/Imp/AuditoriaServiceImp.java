package com.unu.ms.MsSecretariaAcademica.service.Imp;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.unu.ms.MsSecretariaAcademica.model.entity.Auditoria;
import com.unu.ms.MsSecretariaAcademica.model.mapper.AuditoriaMapper;
import com.unu.ms.MsSecretariaAcademica.repository.AuditoriaRepository;
import com.unu.ms.MsSecretariaAcademica.service.AuditoriaService;

@Slf4j
@Service
public class AuditoriaServiceImp implements AuditoriaService {

    AuditoriaRepository auditoriaRepository;
    AuditoriaMapper mapper;

    @Override
    public void registrar(String nombreEntidad, Integer entidadId, String accion, Integer usuarioId, String datosAntes,
            String datosDespues, String observacion) {
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

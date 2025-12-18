package com.unu.ms.MsSecretariaAcademica.service;

public interface AuditoriaService {

     public void registrar(String nombreEntidad, Integer entidadId, String accion, Integer usuarioId, String datosAntes, String datosDespues, String observacion);

}
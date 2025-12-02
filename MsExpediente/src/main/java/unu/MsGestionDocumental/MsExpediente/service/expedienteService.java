package unu.MsGestionDocumental.MsExpediente.service;

import unu.MsGestionDocumental.MsExpediente.repository.ExpedienteRepository;
import unu.MsGestionDocumental.MsExpediente.exception.ResourceNotFoundException;
import unu.MsGestionDocumental.MsExpediente.model.request.*;
import unu.MsGestionDocumental.MsExpediente.model.response.*;

import java.time.LocalDateTime;
import java.util.List;


public interface expedienteService {
    List<responseExpediente> listar();

    responseExpediente obtenerPorId(Integer id);

    responseExpediente guardar(requestExpediente producto);

    void eliminar(Integer id);
}
package unu.MsGestionDocumentaria.MsExpediente.service;

import unu.MsGestionDocumentaria.MsExpediente.repository.repositoryExpediente;
import unu.MsGestionDocumentaria.MsExpediente.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;


public interface expedienteService {
    List<ResponseExpediente> listar();

    ResponseExpediente obtenerPorId(Integer id);

    ResponseExpediente guardar(DemoRequest producto);

    void eliminar(Integer id);
}
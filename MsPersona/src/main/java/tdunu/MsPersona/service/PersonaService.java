package tdunu.MsPersona.service;

import java.util.List;
import tdunu.MsPersona.model.request.PersonaRequest;
import tdunu.MsPersona.model.response.PersonaResponse;

public interface PersonaService {

    List<PersonaResponse> listar();

    PersonaResponse obtenerPorId(Integer id);

    PersonaResponse guardar(PersonaRequest request);

    void eliminar(Integer id);
}

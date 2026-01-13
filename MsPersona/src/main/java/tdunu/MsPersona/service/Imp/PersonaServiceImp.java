package tdunu.MsPersona.service.Imp;

import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdunu.MsPersona.model.entity.PersonaModel;
import tdunu.MsPersona.model.request.PersonaRequest;
import tdunu.MsPersona.model.response.PersonaResponse;
import tdunu.MsPersona.repository.PersonaRepository;
import tdunu.MsPersona.service.PersonaService;

@Slf4j
@Service
public class PersonaServiceImp implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PersonaResponse> listar() {
        return personaRepository.findAll()
            .stream()
            .map(p -> modelMapper.map(p, PersonaResponse.class))
            .toList();
    }

    @Override
    public PersonaResponse obtenerPorId(Integer id) {
        return personaRepository.findById(id)
            .map(p -> modelMapper.map(p, PersonaResponse.class))
            .orElse(null);
    }

    @Override
    public PersonaResponse guardar(PersonaRequest request) {

        if (personaRepository.existsByPerDni(request.getPerDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }

        PersonaModel persona = modelMapper.map(request, PersonaModel.class);
        persona.setFechaCreacion(LocalDateTime.now());
        persona.setUsuarioCreacion("SISTEMA");

        PersonaModel saved = personaRepository.save(persona);
        return modelMapper.map(saved, PersonaResponse.class);
    }

    @Override
    public void eliminar(Integer id) {
        personaRepository.deleteById(id);
    }
}

package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.Multa;
import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import TDUNU2025.Msbiblioteca.repository.MultaRepository;
import TDUNU2025.Msbiblioteca.service.MultaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MultaServiceImpl implements MultaService {

    private final MultaRepository repo;
    private final ModelMapper modelMapper;

    @Override
    public List<MultaResponse> listar() {
        return repo.findAll().stream()
                .map(multa -> modelMapper.map(multa, MultaResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public MultaResponse obtener(Long id) { // ID Long
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multa", "id", id));
        return modelMapper.map(multa, MultaResponse.class);
    }

    @Override
    public MultaResponse registrar(MultaRequest request) {
        Multa entity = modelMapper.map(request, Multa.class);
        
        // Lógica de negocio
        entity.setFechaGeneracion(LocalDate.now());
        entity.setFechaPago(null);
        entity.setDiasRetraso(0);

        Multa saved = repo.save(entity);
        return modelMapper.map(saved, MultaResponse.class);
    }

    @Override
    public MultaResponse actualizar(Long id, MultaRequest request) { // ID Long
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Multa", "id", id));

        modelMapper.map(request, multa);
        multa.setIdMulta(id); // Asegurar ID

        Multa updated = repo.save(multa);
        return modelMapper.map(updated, MultaResponse.class);
    }

    @Override
    public void eliminar(Long id) { // ID Long
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Multa", "id", id);
        }
        repo.deleteById(id);
    }
}
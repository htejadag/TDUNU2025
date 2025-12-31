package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Multa;
import TDUNU2025.Msbiblioteca.model.request.MultaRequest;
import TDUNU2025.Msbiblioteca.model.response.MultaResponse;
import TDUNU2025.Msbiblioteca.repository.MultaRepository;
import TDUNU2025.Msbiblioteca.service.MultaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MultaServiceImpl implements MultaService {

    private final MultaRepository repo;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<MultaResponse> listar() {
        return repo.findAll().stream()
                .map(multa -> modelMapper.map(multa, MultaResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public MultaResponse obtener(Integer id) {
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new BusinessException("Multa no encontrada con ID: " + id));

        return modelMapper.map(multa, MultaResponse.class);
    }

    @Override
    @Transactional
    public MultaResponse registrar(MultaRequest request) {
        validarDatosMulta(request);

        Multa entity = modelMapper.map(request, Multa.class);

        entity.setFechaGeneracion(LocalDate.now());
        entity.setFechaPago(null); 
        
        if (entity.getIdEstadoMulta() == null) {
            entity.setIdEstadoMulta(1); 
        }

        if (entity.getDiasRetraso() == null) {
            entity.setDiasRetraso(0);
        }

        Multa saved = repo.save(entity);
        log.info("Multa generada para Usuario ID: {} por monto: {}", saved.getIdUsuario(), saved.getMonto());

        return modelMapper.map(saved, MultaResponse.class);
    }

    @Override
    @Transactional
    public MultaResponse actualizar(Integer id, MultaRequest request) {
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Multa no encontrada"));

        validarDatosMulta(request);

 
        modelMapper.map(request, multa);
        multa.setIdMulta(id); 

        Multa updated = repo.save(multa);
        return modelMapper.map(updated, MultaResponse.class);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new BusinessException("La multa no existe");
        }
        repo.deleteById(id);
        log.warn("Multa eliminada ID: {}", id);
    }
    
    @Transactional
    public MultaResponse registrarPago(Integer id) {
        Multa multa = repo.findById(id)
                .orElseThrow(() -> new BusinessException("Multa no encontrada"));

        if (multa.getIdEstadoMulta() == 2) {
            throw new BusinessException("Esta multa ya se encuentra pagada");
        }

        multa.setIdEstadoMulta(2); 
        multa.setFechaPago(LocalDate.now()); 
        
        Multa pagada = repo.save(multa);
        log.info("Multa ID {} pagada el {}", id, LocalDate.now());
        
        return modelMapper.map(pagada, MultaResponse.class);
    }

    private void validarDatosMulta(MultaRequest request) {
        if (request.getMonto() == null || request.getMonto() < 0) {
            throw new BusinessException("El monto de la multa debe ser válido y positivo");
        }
        if (request.getIdUsuario() == null) {
            throw new BusinessException("Debe indicar el usuario a multar");
        }
        if (request.getIdPrestamo() == null) {
            throw new BusinessException("Debe asociar la multa a un préstamo");
        }
    }
}
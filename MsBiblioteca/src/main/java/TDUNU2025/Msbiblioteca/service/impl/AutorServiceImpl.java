package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.BusinessException;
import TDUNU2025.Msbiblioteca.model.entity.Autor;
import TDUNU2025.Msbiblioteca.model.request.AutorRequest;
import TDUNU2025.Msbiblioteca.model.response.AutorResponse;
import TDUNU2025.Msbiblioteca.repository.AutorRepository;
import TDUNU2025.Msbiblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; 
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j 
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true) 
    public List<AutorResponse> listarAutores() {
        return autorRepository.findAll()
                .stream()
                .map(autor -> modelMapper.map(autor, AutorResponse.class))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AutorResponse obtenerAutorPorId(Integer id) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Autor no encontrado"));
        return modelMapper.map(autor, AutorResponse.class);
    }

    @Override
    @Transactional 
    public AutorResponse guardarAutor(AutorRequest request) {

        validarDatosAutor(request);

        log.info("Intentando guardar un nuevo autor con ID Persona: {}", request.getIdPersona());
        
        Autor autor = modelMapper.map(request, Autor.class);
        Autor guardado = autorRepository.save(autor);
        
        log.info("Autor guardado correctamente con ID: {}", guardado.getIdAutor());
        return modelMapper.map(guardado, AutorResponse.class);
    }

    @Override
    @Transactional
    public AutorResponse actualizarAutor(Integer id, AutorRequest request) {
        Autor autor = autorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Autor no encontrado para actualizar"));

        validarDatosAutor(request);

        modelMapper.map(request, autor);

        autor.setIdAutor(id); 

        Autor actualizado = autorRepository.save(autor);
        log.info("Autor actualizado correctamente con ID: {}", id);
        
        return modelMapper.map(actualizado, AutorResponse.class);
    }

    @Override
    @Transactional
    public void eliminarAutor(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new BusinessException("No se puede eliminar: Autor no encontrado");
        }
        autorRepository.deleteById(id);
        log.warn("Autor con ID {} eliminado del sistema", id);
    }

    private void validarDatosAutor(AutorRequest request) {
        if (request.getIdPersona() == null || request.getIdPersona() <= 0) {
            throw new BusinessException("El ID de la persona es obligatorio y debe ser válido");
        }

        if (request.getBiografia() != null && request.getBiografia().trim().isEmpty()) {
             throw new BusinessException("La biografía no puede estar vacía si se envía");
        }

    }
}
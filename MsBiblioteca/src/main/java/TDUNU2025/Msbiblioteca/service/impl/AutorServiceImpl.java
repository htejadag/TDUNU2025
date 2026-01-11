package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.config.BusinessException; // Importamos tu nueva excepción
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
                .orElseThrow(() -> new BusinessException("Autor no encontrado con ID: " + id));
        return modelMapper.map(autor, AutorResponse.class);
    }

    @Override
    @Transactional
    public AutorResponse guardarAutor(AutorRequest request) {
        validarDatosEntrada(request);

        if (autorRepository.existsByIdPersona(request.getIdPersona())) {
            throw new BusinessException("Ya existe un Autor registrado con ese ID de Persona");
        }

        Autor autor = modelMapper.map(request, Autor.class);
        Autor guardado = autorRepository.save(autor);
        
        log.info("Autor guardado: {}", guardado.getIdAutor());
        return modelMapper.map(guardado, AutorResponse.class);
    }

    @Override
    @Transactional
    public AutorResponse actualizarAutor(Integer id, AutorRequest request) {
        Autor autorExistente = autorRepository.findById(id)
                .orElseThrow(() -> new BusinessException("No se puede actualizar: Autor no encontrado"));

        validarDatosEntrada(request);

        modelMapper.map(request, autorExistente);
        autorExistente.setIdAutor(id); 

        Autor actualizado = autorRepository.save(autorExistente);
        return modelMapper.map(actualizado, AutorResponse.class);
    }

    @Override
    @Transactional
    public void eliminarAutor(Integer id) {
        if (!autorRepository.existsById(id)) {
            throw new BusinessException("No se puede eliminar: Autor no encontrado");
        }
        autorRepository.deleteById(id);
    }

    private void validarDatosEntrada(AutorRequest request) {
        if (request == null) throw new BusinessException("El request es nulo");
        if (request.getIdPersona() == null) throw new BusinessException("El ID Persona es obligatorio");
    }
}
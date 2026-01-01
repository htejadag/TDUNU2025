package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.exception.ResourceNotFoundException;
import TDUNU2025.Msbiblioteca.model.entity.Catalogo;
import TDUNU2025.Msbiblioteca.model.request.CatalogoRequest;
import TDUNU2025.Msbiblioteca.model.response.CatalogoResponse;
import TDUNU2025.Msbiblioteca.repository.CatalogoRepository;
import TDUNU2025.Msbiblioteca.service.CatalogoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

    private final CatalogoRepository catalogoRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CatalogoResponse> listar() {
        List<Catalogo> catalogos = catalogoRepository.findAll();
        
        return catalogos.stream()
                // CORREGIDO: Mapeamos 'catalogo' (singular), no la lista
                .map(catalogo -> modelMapper.map(catalogo, CatalogoResponse.class)) 
                .collect(Collectors.toList());
    }

    @Override
    public CatalogoResponse obtener(Long id) {
        Catalogo catalogo = catalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogo", "id", id));
        
        return modelMapper.map(catalogo, CatalogoResponse.class);
    }

    @Override
    public CatalogoResponse registrar(CatalogoRequest request) {
        Catalogo catalogo = modelMapper.map(request, Catalogo.class);
        
        // Estado por defecto (si es null, lo ponemos activo 1)
        if(catalogo.getEstado() == null) {
            catalogo.setEstado(1);
        }

        Catalogo catalogoGuardado = catalogoRepository.save(catalogo);
        return modelMapper.map(catalogoGuardado, CatalogoResponse.class);
    }

    @Override
    public CatalogoResponse actualizar(Long id, CatalogoRequest request) {
        // 1. Verificar existencia
        Catalogo catalogoExistente = catalogoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalogo", "id", id));

        // 2. Actualizar datos
        modelMapper.map(request, catalogoExistente);

        // 3. Asegurar ID
        catalogoExistente.setIdCatalogo(id);

        // 4. Guardar (CORREGIDO: Tipo Catalogo, no Autor)
        Catalogo catalogoActualizado = catalogoRepository.save(catalogoExistente);

        // 5. Devolver
        return modelMapper.map(catalogoActualizado, CatalogoResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!catalogoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Catalogo", "id", id);
        }
        catalogoRepository.deleteById(id);
    }
}
package tdunu2025.msbiblioteca.service.impl;

import tdunu2025.msbiblioteca.exception.ResourceNotFoundException;
import tdunu2025.msbiblioteca.model.entity.Autor;
import tdunu2025.msbiblioteca.model.request.AutorRequest;
import tdunu2025.msbiblioteca.model.response.AutorResponse;
import tdunu2025.msbiblioteca.repository.AutorRepository;
import tdunu2025.msbiblioteca.service.AutorService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service 
@RequiredArgsConstructor
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AutorResponse> listar() {
        //obtencion de la lista de la base de datos
        List<Autor> autores = autorRepository.findAll();
        //convirtiendolo la entidad a response usando stram
        return autores.stream()
        .map(autor -> modelMapper.map(autor, AutorResponse.class))
        .toList();
    }
    
    @Override
    //buscamos a autor, en caso de no existir , enviamos mensaje personalizado
    public AutorResponse obtener(Long id) {
        Autor autor = autorRepository.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Autor", "id", id));

        return modelMapper.map(autor, AutorResponse.class);

    }

    @Override
    public AutorResponse registrar(AutorRequest request) {
        Autor autor = modelMapper.map(request, Autor.class);

        Autor autoGuardado = autorRepository.save(autor);

        return modelMapper.map(autoGuardado, AutorResponse.class);
    }

    @Override
    public void eliminar(Long id) {
        if (!autorRepository.existsById(id)){
            throw new ResourceNotFoundException("autor", "id", id);
        }
           autorRepository.deleteById(id);
         
    }

    @Override
    public AutorResponse actualizar(Long id, AutorRequest request) {
        //verificar si existe en la bd
        Autor autorExistente = autorRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("Autor", "id", id));

        //actualizar los datos
        modelMapper.map(request, autorExistente);

        //Aseguramos de que el id no se pierda ni se cambien por error
        autorExistente.setIdAutor(id);

        //guardamos los cambios
        Autor autorActualizado = autorRepository.save(autorExistente);

        //devolvemos el response
        return modelMapper.map(autorActualizado, AutorResponse.class);

    }


}
package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.Msbiblioteca.model.entity.Autor;
import TDUNU2025.Msbiblioteca.repository.AutorRepository;
import TDUNU2025.Msbiblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service 
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @Override
    public Optional<Autor> obtenerAutorPorId(Integer id) {
        return autorRepository.findById(id);
    }

    @Override
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminarAutor(Integer id) {
        autorRepository.deleteById(id);
    }
}
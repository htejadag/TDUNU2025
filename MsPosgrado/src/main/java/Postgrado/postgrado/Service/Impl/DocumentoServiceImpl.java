package Postgrado.postgrado.Service.Impl;

import Postgrado.postgrado.Model.Documento;
import Postgrado.postgrado.Repository.DocumentoRepository;
import Postgrado.postgrado.Service.DocumentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository repository;

    public DocumentoServiceImpl(DocumentoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Documento crear(Documento documento) {
        return repository.save(documento);
    }

    @Override
    public List<Documento> listar() {
        return repository.findByActivoTrue();
    }

    @Override
    public Documento obtenerPorId(Integer id) {
        return repository.findById(id).filter(Documento::getActivo).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        Documento documento = obtenerPorId(id);
        if (documento != null) {
            documento.setActivo(false);
            repository.save(documento);
        }
    }
}

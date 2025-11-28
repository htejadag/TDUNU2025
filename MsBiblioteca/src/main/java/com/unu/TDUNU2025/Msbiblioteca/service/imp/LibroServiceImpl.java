package main.java.com.unu.TDUNU2025.Msbiblioteca.service.imp;

import com.unu.TDUNU2025.Msbiblioteca.model.entity.Libro;
import com.unu.TDUNU2025.Msbiblioteca.model.request.LibroRequest;
import com.unu.TDUNU2025.Msbiblioteca.model.response.LibroResponse;
import com.unu.TDUNU2025.Msbiblioteca.repository.LibroRepository;
import com.unu.TDUNU2025.Msbiblioteca.service.LibroService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repo;

    private LibroResponse map(Libro libro) {
        return LibroResponse.builder()
                .idLibro(libro.getIdLibro())
                .isbn(libro.getIsbn())
                .titulo(libro.getTitulo())
                .subtitulo(libro.getSubtitulo())
                .descripcion(libro.getDescripcion())
                .numeroPaginas(libro.getNumeroPaginas())
                .idioma(libro.getIdioma())
                .fechaPublicacion(libro.getFechaPublicacion())
                .edicion(libro.getEdicion())
                .codigoDewey(libro.getCodigoDewey())
                .portadaUrl(libro.getPortadaUrl())
                .archivoDigitalUrl(libro.getArchivoDigitalUrl())
                .idEditorial(libro.getIdEditorial())
                .idEstadoLibro(libro.getIdEstadoLibro())
                .fechaRegistro(libro.getFechaRegistro())
                .build();
    }

    @Override
    public LibroResponse registrar(LibroRequest r) {

        if (repo.existsByIsbn(r.getIsbn())) {
            throw new RuntimeException("El ISBN ya existe");
        }

        Libro libro = Libro.builder()
                .isbn(r.getIsbn())
                .titulo(r.getTitulo())
                .subtitulo(r.getSubtitulo())
                .descripcion(r.getDescripcion())
                .numeroPaginas(r.getNumeroPaginas())
                .idioma(r.getIdioma())
                .fechaPublicacion(r.getFechaPublicacion())
                .endicion(r.getEdicion())
                .codigoDewey(r.getCodigoDewey())
                .portadaUrl(r.getPortadaUrl())
                .archivoDigitalUrl(r.getArchivoDigitalUrl())
                .idEditorial(r.getIdEditorial())
                .idEstadoLibro(r.getIdEstadoLibro())
                .build();

        repo.save(libro);
        return map(libro);
    }

    @Override
    public List<LibroResponse> listar() {
        return repo.findAll().stream().map(this::map).toList();
    }

    @Override
    public LibroResponse obtener(Long id) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return map(libro);
    }

    @Override
    public LibroResponse actualizar(Long id, LibroRequest r) {
        Libro libro = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libro.setIsbn(r.getIsbn());
        libro.setTitulo(r.getTitulo());
        libro.setSubtitulo(r.getSubtitulo());
        libro.setDescripcion(r.getDescripcion());
        libro.setNumeroPaginas(r.getNumeroPaginas());
        libro.setIdioma(r.getIdioma());
        libro.setFechaPublicacion(r.getFechaPublicacion());
        libro.setEdicion(r.getEdicion());
        libro.setCodigoDewey(r.getCodigoDewey());
        libro.setPortadaUrl(r.getPortadaUrl());
        libro.setArchivoDigitalUrl(r.getArchivoDigitalUrl());
        libro.setIdEditorial(r.getIdEditorial());
        libro.setIdEstadoLibro(r.getIdEstadoLibro());

        repo.save(libro);
        return map(libro);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}

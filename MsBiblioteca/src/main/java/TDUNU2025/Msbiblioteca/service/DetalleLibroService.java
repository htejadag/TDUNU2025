package TDUNU2025.Msbiblioteca.service;

<<<<<<< HEAD
import java.util.List;

import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

public interface DetalleLibroService {

    DetalleLibroResponse save(DetalleLibroRequest request);
    DetalleLibroResponse findById(Integer idDetalleLibro);
    List<DetalleLibroResponse> findAll();
    DetalleLibroResponse update(Integer idDetalleLibro, DetalleLibroRequest request);
    void delete(Integer idDetalleLibro);
}
=======
import TDUNU2025.Msbiblioteca.model.request.DetalleLibroRequest;
import TDUNU2025.Msbiblioteca.model.response.DetalleLibroResponse;

import java.util.List;

public interface DetalleLibroService {

    List<DetalleLibroResponse> listar();

    DetalleLibroResponse obtener(Long id);

    DetalleLibroResponse registrar(DetalleLibroRequest request);

    DetalleLibroResponse actualizar(Long id, DetalleLibroRequest request);

    void eliminar(Long id);
}
>>>>>>> origin/origin/jordinTrujillo

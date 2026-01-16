package tdunu.MsCatalogo.service;

import tdunu.MsCatalogo.model.request.CatalogoRequest;
import tdunu.MsCatalogo.model.response.CatalogoResponse;

import java.util.List;

public interface CatalogoService {

    List<CatalogoResponse> listar();

    CatalogoResponse obtenerPorId(Integer id);

    CatalogoResponse guardar(CatalogoRequest request);

    CatalogoResponse actualizar(Integer id, CatalogoRequest request);

    void eliminar(Integer id);

    List<CatalogoResponse> buscarPorTipo(Integer tipoCatalogoId);

    CatalogoResponse buscarPorCodigo(Integer tipoCatalogoId, String codigo);

    void limpiarCache();
}

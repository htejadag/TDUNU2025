package tdunu.MsCatalogo.service;

import tdunu.MsCatalogo.model.request.TipoCatalogoRequest;
import tdunu.MsCatalogo.model.response.TipoCatalogoResponse;

import java.util.List;

public interface TipoCatalogoService {

    List<TipoCatalogoResponse> listar();

    TipoCatalogoResponse obtenerPorId(Integer id);

    TipoCatalogoResponse guardar(TipoCatalogoRequest request);

    TipoCatalogoResponse actualizar(Integer id, TipoCatalogoRequest request);

    void eliminar(Integer id);
}

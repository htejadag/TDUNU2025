package unu.td.msacademico.service;

import unu.td.msacademico.model.request.CatalogoRequest;
import unu.td.msacademico.model.response.CatalogoResponse;
import unu.td.msacademico.utils.IService;

import java.util.List;

public interface ICatalogoService extends IService<CatalogoResponse, CatalogoRequest> {

    public List<CatalogoResponse> getAllByCategoria(String categoria);

}

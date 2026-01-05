package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.request.CatalogoRequest;
import unu.td.MsAcademico.model.response.CatalogoResponse;
import unu.td.MsAcademico.utils.IService;

import java.util.List;

public interface ICatalogoService extends IService<CatalogoResponse, CatalogoRequest> {

    public List<CatalogoResponse> getAllByCategoria(String categoria);

}

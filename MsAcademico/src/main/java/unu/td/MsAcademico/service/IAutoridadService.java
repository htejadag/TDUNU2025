package unu.td.MsAcademico.service;

import unu.td.MsAcademico.model.request.AutoridadRequest;
import unu.td.MsAcademico.model.response.AutoridadResponse;
import unu.td.MsAcademico.utils.IService;

import java.time.LocalDate;
import java.util.List;

public interface IAutoridadService extends IService<AutoridadResponse, AutoridadRequest> {

    List<AutoridadResponse> getByIdEntidad(Integer idTipoEntidad, Integer idEntidad);

    AutoridadResponse getByIdEntidadAndFecha(Integer idTipoEntidad, Integer idEntidad, LocalDate fecha);

}

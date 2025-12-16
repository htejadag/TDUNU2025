package unu.td.msacademico.service;

import unu.td.msacademico.model.request.AutoridadRequest;
import unu.td.msacademico.model.response.AutoridadResponse;
import unu.td.msacademico.utils.IService;

import java.time.LocalDate;
import java.util.List;

public interface IAutoridadService extends IService<AutoridadResponse, AutoridadRequest> {

    List<AutoridadResponse> getByEntidad(Integer idTipoEntidad, Integer idEntidad);

    AutoridadResponse getByEntidadAndFecha(Integer idTipoEntidad, Integer idEntidad, LocalDate fecha);

}

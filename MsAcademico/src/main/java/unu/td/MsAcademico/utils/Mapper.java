package unu.td.MsAcademico.utils;

import unu.td.MsAcademico.model.entity.AutoridadModel;
import unu.td.MsAcademico.model.request.AutoridadRequest;

public class Mapper {

    public static class Autoridad {
        public static AutoridadModel requestToModel(AutoridadRequest request) {
            AutoridadModel model = new AutoridadModel();

            model.setIdUsuario(request.getIdUsuario());
            model.setIdTipoAutoridad(request.getIdTipoAutoridad());
            model.setIdEntidad(request.getIdEntidad());
            model.setIdTipoEntidad(request.getIdTipoEntidad());
            model.setFechaInicio(request.getFechaInicio());
            model.setFechaFin(request.getFechaFin());

            return model;
        }
    }
}
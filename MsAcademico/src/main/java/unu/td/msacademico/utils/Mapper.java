package unu.td.msacademico.utils;

import unu.td.msacademico.model.entity.AutoridadModel;
import unu.td.msacademico.model.request.AutoridadRequest;

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

        public static AutoridadModel requestToModelUpdate(AutoridadModel model, AutoridadRequest request) {
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
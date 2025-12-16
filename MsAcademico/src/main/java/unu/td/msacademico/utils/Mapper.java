package unu.td.msacademico.utils;

import unu.td.msacademico.model.entity.AutoridadModel;
import unu.td.msacademico.model.entity.CatalogoModel;
import unu.td.msacademico.model.entity.EscuelaProfesionalModel;
import unu.td.msacademico.model.entity.FacultadModel;
import unu.td.msacademico.model.request.AutoridadRequest;
import unu.td.msacademico.model.request.CatalogoRequest;
import unu.td.msacademico.model.request.EscuelaProfesionalRequest;
import unu.td.msacademico.model.request.FacultadRequest;

public class Mapper {

    public static class Facultad {
        public static FacultadModel requestToModel(FacultadModel model, FacultadRequest request) {
            if (model == null) {
                model = new FacultadModel();
            }
            model.setNombre(request.getNombre());
            model.setDescripcion(request.getDescripcion());
            model.setFechaFundacion(request.getFechaFundacion());

            return model;
        }
    }

    public static class Escuela {
        public static EscuelaProfesionalModel requestToModel(EscuelaProfesionalModel model, EscuelaProfesionalRequest request) {
            if (model == null) {
                model = new EscuelaProfesionalModel();
            }
            model.setNombre(request.getNombre());
            model.setDescripcion(request.getDescripcion());
            model.setFechaFundacion(request.getFechaFundacion());

            return model;
        }
    }

    public static class Autoridad {
        public static AutoridadModel requestToModel(AutoridadModel model, AutoridadRequest request) {
            if (model == null) {
                model = new AutoridadModel();
            }
            model.setIdUsuario(request.getIdUsuario());
            model.setIdEntidad(request.getIdEntidad());
            model.setFechaInicio(request.getFechaInicio());
            model.setFechaFin(request.getFechaFin());

            return model;
        }
    }

    public static class Catalogo {
        public static CatalogoModel requestToModel(CatalogoModel model, CatalogoRequest request) {
            if (model == null) {
                model = new CatalogoModel();
            }
            model.setCategoria(request.getCategoria());
            model.setNombre(request.getNombre());
            model.setAbreviatura(request.getAbreviatura());
            model.setValor(request.getValor());
            model.setOrden(request.getOrden());

            return model;
        }
    }
}
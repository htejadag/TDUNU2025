package unu.td.MsAcademico.utils;

import unu.td.MsAcademico.model.entity.EscuelaProfesionalModel;
import unu.td.MsAcademico.model.request.EscuelaProfesionalRequest;

public class Mapper {

    public static class Escuela {
        public static EscuelaProfesionalModel requestToModel(EscuelaProfesionalRequest request) {
            EscuelaProfesionalModel model = new EscuelaProfesionalModel();
            model.setNombre(request.getNombre());
            model.setDuracionCarrera(request.getDuracionCarrera());
            return  model;
        }
    }
}

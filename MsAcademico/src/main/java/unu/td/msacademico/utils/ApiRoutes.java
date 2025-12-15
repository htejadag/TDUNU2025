package unu.td.msacademico.utils;

public class ApiRoutes {

    public static class General {
        public static final String add = "/add";
        public static final String update = "/udpate/{id}";
        public static final String delete = "/delete/{id}";
        public static final String deactivate = "/deactivate/{id}";
        public static final String activate = "/activate/{id}";
        public static final String byId = "/{id}";
    }

    public static class Facultad {
        public Facultad() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/facultades";
    }

    public static class EscuelaProfesional {
        public EscuelaProfesional() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/escuelas";
        public static final String byIdFacultad = "/byIdFacultad/{idFacultad}";
    }

    public static class Autoridad {
        public Autoridad() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/autoridades";
        public static final String byIdEntidad = "/byIdEntidad/{idTipoEntidad}/{idEntidad}";
        public static final String byIdEntidadAndFecha = "/byIdEntidadAndFecha/{idTipoEntidad}/{idEntidad}/{fecha}";
    }

    public static class Catalogo {
        public Catalogo() {
            throw new UnsupportedOperationException();
        }

        public static final String BASE = "/api/catalogo";
    }
}

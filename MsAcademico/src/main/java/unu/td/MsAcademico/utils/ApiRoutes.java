package unu.td.MsAcademico.utils;

public class ApiRoutes {

    public static class General {
        public static final String add = "/add";
        public static final String update = "/udpate/{id}";
        public static final String delete = "/delete/{id}";
        public static final String deactivate = "/deactivate/{id}";
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
    }
}

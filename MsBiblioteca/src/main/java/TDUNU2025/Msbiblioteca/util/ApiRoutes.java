package TDUNU2025.Msbiblioteca.util;

public class ApiRoutes {

    public static final String RUTA_AUTOR = "/api/autor";

    // =========================
    // ✅ RUTAS DE LIBRO
    // =========================
    public static class Libro {
        public static final String BASE = "/api/libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";

        // ¡IMPORTANTE! Las llaves {id} son necesarias para @PathVariable
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================

    // ✅ RUTAS DE MULTA
    // =========================
    public static class Multa {
        public static final String BASE = "/api/multa";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";

        // ¡IMPORTANTE! Las llaves {id} son necesarias para @PathVariable
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE LIBRO - AUTOR (NUEVO ✅)
    // =========================
    public static class LibroAutor {
        public static final String BASE = "/api/libro-autor";

        // =========================
        // ✅ RUTAS DE Prestamo (AGREGADO)
        // =========================
        public static class Prestamo {
            public static final String BASE = "/api/prestamo";
            public static final String GUARDAR = "/guardar";
            public static final String LISTAR = "/listar";

            // ¡IMPORTANTE! Las llaves {id} son necesarias para @PathVariable
            public static final String OBTENER_POR_ID = "/obtener/{id}";
            public static final String ACTUALIZAR = "/actualizar/{id}";
            public static final String ELIMINAR = "/eliminar/{id}";
        }

    } // Close DetalleLibro class

    public static class DetalleLibro {
        public static final String BASE = "/api/detalle-libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
    } // Close DetalleLibro class

    // =========================
    // ✅ RUTAS de Catalogo (AGREGADO)
    // =========================
    public static class Catalogo {
        public static final String BASE = "/api/catalogo";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";

        // ¡IMPORTANTE! Las llaves {id} son necesarias for @PathVariable
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    public static class LibroCategoria {
        public static final String BASE = "/api/libro-categoria";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";

        // =========================
        // ✅ RUTAS DE DetalleUsuario (AGREGADO)
        // =========================
        public static class DetalleUsuario {
            public static final String BASE = "/api/detalle-usuario";
            public static final String GUARDAR = "/guardar";
            public static final String LISTAR = "/listar";

            // ¡IMPORTANTE! Las llaves {id} son necesarias para @PathVariable
            public static final String OBTENER_POR_ID = "/obtener/{id}";
            public static final String ACTUALIZAR = "/actualizar/{id}";
            public static final String ELIMINAR = "/eliminar/{id}";
        }
    }
}

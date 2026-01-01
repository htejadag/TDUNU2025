package TDUNU2025.Msbiblioteca.util;

public class ApiRoutes {

    // Ruta base para Autores (Simple, sin clase anidada si prefieres, o la estandarizamos abajo)
    public static final String RUTA_AUTOR = "/api/autor";

    // =========================
    // ✅ RUTAS DE LIBRO
    // =========================
    public static class Libro {
        public static final String BASE = "/api/libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
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
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE PRESTAMO (Faltaba esta, la agrego por lógica)
    // =========================
    public static class Prestamo {
        public static final String BASE = "/api/prestamo";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE CATALOGO
    // =========================
    public static class Catalogo {
        public static final String BASE = "/api/catalogo";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE DETALLE LIBRO
    // =========================
    public static class DetalleLibro {
        public static final String BASE = "/api/detalle-libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE DETALLE USUARIO
    // =========================
    public static class DetalleUsuario {
        public static final String BASE = "/api/detalle-usuario";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}"; // Faltaban estos métodos comunes
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // ✅ RUTAS DE LIBRO CATEGORIA
    // =========================
    public static class LibroCategoria {
        public static final String BASE = "/api/libro-categoria";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }
}
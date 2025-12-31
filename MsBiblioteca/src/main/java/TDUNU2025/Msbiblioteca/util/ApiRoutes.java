package TDUNU2025.Msbiblioteca.util;

public class ApiRoutes {

    private ApiRoutes() {
        throw new IllegalStateException("Utility class");
    }

    public static final String RUTA_AUTOR = "/api/autor";

    // =========================
    // MODULO: LIBRO
    // =========================
    public static class Libro {
        private Libro() {} // Constructor privado para la sub-clase
        
        public static final String BASE = "/api/libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // MODULO: MULTA
    // =========================
    public static class Multa {
        private Multa() {}
        
        public static final String BASE = "/api/multa";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // MODULO: LIBRO - AUTOR
    // =========================
    public static class LibroAutor {
        private LibroAutor() {}
        public static final String BASE = "/api/libro-autor";
    }

    // =========================
    // MODULO: PRESTAMO (Corregido: Sacado fuera de LibroAutor)
    // =========================
    public static class Prestamo {
        private Prestamo() {}
        
        public static final String BASE = "/api/prestamo";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // MODULO: DETALLE LIBRO
    // =========================
    public static class DetalleLibro {
        private DetalleLibro() {}
        
        public static final String BASE = "/api/detalle-libro";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
    }

    // =========================
    // MODULO: CATALOGO
    // =========================
    public static class Catalogo {
        private Catalogo() {}
        
        public static final String BASE = "/api/catalogo";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }

    // =========================
    // MODULO: LIBRO CATEGORIA
    // =========================
    public static class LibroCategoria {
        private LibroCategoria() {}
        public static final String BASE = "/api/libro-categoria";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
    }

    // =========================
    // MODULO: DETALLE USUARIO (Corregido: Sacado fuera de LibroCategoria)
    // =========================
    public static class DetalleUsuario {
        private DetalleUsuario() {}
        
        public static final String BASE = "/api/detalle-usuario";
        public static final String GUARDAR = "/guardar";
        public static final String LISTAR = "/listar";
        public static final String OBTENER_POR_ID = "/obtener/{id}";
        public static final String ACTUALIZAR = "/actualizar/{id}";
        public static final String ELIMINAR = "/eliminar/{id}";
    }
}
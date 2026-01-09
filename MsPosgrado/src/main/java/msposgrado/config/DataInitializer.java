package msposgrado.config;

import msposgrado.Model.EstadoSolicitud;
import msposgrado.Model.TipoSolicitud;
import msposgrado.Repository.EstadoSolicitudRepository;
import msposgrado.Repository.TipoSolicitudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Clase encargada de inicializar datos básicos del sistema
 * al momento de arrancar la aplicación.
 *
 * <p>
 * Implementa {@link CommandLineRunner}, lo que permite ejecutar
 * lógica automática una vez que el contexto de Spring ha sido cargado.
 * </p>
 *
 * <p>
 * Esta clase verifica la existencia de:
 * <ul>
 *   <li>Tipos de solicitud</li>
 *   <li>Estados de solicitud</li>
 * </ul>
 * y los crea automáticamente si no existen.
 * </p>
 *
 * <p>
 * Evita duplicados verificando previamente la base de datos.
 * </p>
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final TipoSolicitudRepository tipoRepository;
    private final EstadoSolicitudRepository estadoRepository;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param tipoRepository   repositorio de tipos de solicitud
     * @param estadoRepository repositorio de estados de solicitud
     */
    public DataInitializer(TipoSolicitudRepository tipoRepository, EstadoSolicitudRepository estadoRepository) {
        this.tipoRepository = tipoRepository;
        this.estadoRepository = estadoRepository;
    }

    /**
     * Método ejecutado automáticamente al iniciar la aplicación.
     *
     * @param args argumentos de línea de comandos
     * @throws Exception en caso de error durante la inicialización
     */
    @Override
    public void run(String... args) throws Exception {
        inicializarTipos();
        inicializarEstados();
    }

    /**
     * Inicializa los tipos de solicitud por defecto del sistema.
     *
     * <p>
     * Los tipos creados son:
     * <ul>
     *   <li>INSCRIPCION_PROYECTO</li>
     *   <li>DESIGNACION_ASESOR</li>
     *   <li>DESIGNACION_JURADO</li>
     *   <li>SUSTENTACION</li>
     * </ul>
     * </p>
     *
     * <p>
     * Cada tipo se crea solo si no existe previamente en la base de datos.
     * </p>
     */
    private void inicializarTipos() {
        List<String> tipos = Arrays.asList(
                "INSCRIPCION_PROYECTO",
                "DESIGNACION_ASESOR",
                "DESIGNACION_JURADO",
                "SUSTENTACION"
        );

        for (String nombre : tipos) {
            if (tipoRepository.findByNombre(nombre).isEmpty()) {
                TipoSolicitud tipo = new TipoSolicitud();
                tipo.setNombre(nombre);
                tipo.setDescripcion("Tipo de solicitud generado automáticamente: " + nombre);
                tipoRepository.save(tipo);
                System.out.println("-> Tipo Solicitud creado: " + nombre);
            }
        }
    }

    /**
     * Inicializa los estados de solicitud por defecto del sistema.
     *
     * <p>
     * Los estados creados son:
     * <ul>
     *   <li>PENDIENTE</li>
     *   <li>EN_REVISION</li>
     *   <li>OBSERVADO</li>
     *   <li>APROBADO</li>
     * </ul>
     * </p>
     *
     * <p>
     * Cada estado se crea solo si no existe previamente en la base de datos.
     * </p>
     */
    private void inicializarEstados() {
        List<String> estados = Arrays.asList(
                "PENDIENTE",
                "EN_REVISION",
                "OBSERVADO",
                "APROBADO"
        );

        for (String nombre : estados) {
            if (estadoRepository.findByNombre(nombre).isEmpty()) {
                EstadoSolicitud estado = new EstadoSolicitud();
                estado.setNombre(nombre);
                estado.setDescripcion("Estado generado automáticamente: " + nombre);
                estadoRepository.save(estado);
                System.out.println("-> Estado Solicitud creado: " + nombre);
            }
        }
    }
}
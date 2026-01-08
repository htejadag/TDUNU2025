package msposgrado.config;

import msposgrado.Model.EstadoSolicitud;
import msposgrado.Model.TipoSolicitud;
import msposgrado.Repository.EstadoSolicitudRepository;
import msposgrado.Repository.TipoSolicitudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final TipoSolicitudRepository tipoRepository;
    private final EstadoSolicitudRepository estadoRepository;

    public DataInitializer(TipoSolicitudRepository tipoRepository, EstadoSolicitudRepository estadoRepository) {
        this.tipoRepository = tipoRepository;
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        inicializarTipos();
        inicializarEstados();
    }

    private void inicializarTipos() {
        List<String> tipos = Arrays.asList(
                "INSCRIPCION_PROYECTO",
                "DESIGNACION_ASESOR",
                "DESIGNACION_JURADO",
                "SUSTENTACION");

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

    private void inicializarEstados() {
        List<String> estados = Arrays.asList(
                "PENDIENTE",
                "EN_REVISION",
                "OBSERVADO",
                "APROBADO");

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

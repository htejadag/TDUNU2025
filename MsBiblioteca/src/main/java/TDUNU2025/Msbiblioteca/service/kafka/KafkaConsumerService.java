package TDUNU2025.Msbiblioteca.service.kafka;

import TDUNU2025.MsBiblioteca.model.event.UsuarioEvent;
import TDUNU2025.MsDetalleUsuario.model.entity.DetalleUsuario; // Referencia a tu tabla de detalles
import TDUNU2025.MsDetalleUsuario.service.DetalleUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KafkaConsumerService {

    @Autowired
    private DetalleUsuarioService detalleUsuarioService; 

    @KafkaListener(topics = "biblioteca.usuarios.actualizaciones", groupId = "group_ms_biblioteca")
    public void escucharEventosUsuarios(UsuarioEvent event) {
        System.out.println("Kafka: Recibido evento de Usuario Externo -> " + event.getTipoEvento());

        if ("USUARIO_NUEVO".equals(event.getTipoEvento())) {

            DetalleUsuario nuevoDetalle = new DetalleUsuario();
            nuevoDetalle.setIdUsuario(event.getIdUsuario());
            nuevoDetalle.setIdDetalleUsuario(0); // O el ID que corresponda
            nuevoDetalle.setTotalPrestamos(0);
            nuevoDetalle.setTotalMultas(0);
            nuevoDetalle.setFechaUltimoPrestamo(null);

            detalleUsuarioService.guardar(nuevoDetalle);
            System.out.println("DetalleUsuario inicializado para ID: " + event.getIdUsuario());
        }
    }
}
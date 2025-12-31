package TDUNU2025.Msbiblioteca.service.impl;

import TDUNU2025.MsPrestamo.model.entity.Prestamo;
import TDUNU2025.MsPrestamo.model.event.PrestamoEvent; 
import TDUNU2025.MsPrestamo.repository.PrestamoRepository;
import TDUNU2025.MsPrestamo.service.PrestamoService;
import TDUNU2025.Msbiblioteca.service.kafka.KafkaProducerService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService; // Inyectamos el productor de Kafka

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> obtenerPrestamoPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        // 1. Guardamos en nuestra base de datos local (como siempre)
        Prestamo guardado = prestamoRepository.save(prestamo);

        // 2. DISPARAMOS EL EVENTO AL ECOSISTEMA
        // Creamos el objeto del evento con la información que los demás necesitan
        PrestamoEvent evento = new PrestamoEvent(
                "PRESTAMO_CREADO",
                guardado.getIdPrestamo(),
                guardado.getIdUsuario(),
                guardado.getIdLibro(),
                LocalDateTime.now(),
                "Nuevo préstamo registrado en msBiblioteca"
        );

        // Enviamos a Kafka
        kafkaProducerService.enviarEventoPrestamo(evento);

        return guardado;
    }

    @Override
    public void eliminarPrestamo(Integer id) {
        prestamoRepository.deleteById(id);
    }
}
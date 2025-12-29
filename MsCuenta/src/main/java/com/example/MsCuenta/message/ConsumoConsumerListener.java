package com.example.MsCuenta.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.MsCuenta.model.request.KafkaEvent.ConsumoKafkaRequest;
import com.example.MsCuenta.service.CuentaUsuarioService;
import com.example.MsCuenta.service.MovimientoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumoConsumerListener {


    private static final Logger log =
            LoggerFactory.getLogger(ConsumoConsumerListener.class);

    @Autowired
    private CuentaUsuarioService cuentaUsuarioService;

    @Autowired
    private MovimientoService movimientoService;

    private  ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(
    topics = "${spring.kafka.template.default-topic}",
    groupId = "${spring.kafka.consumer.group-id}"
)
    public void onMessage(ConsumerRecord<Integer, String> record) {

        try {
            ConsumoKafkaRequest data =
                objectMapper.readValue(record.value(), ConsumoKafkaRequest.class);

            cuentaUsuarioService.descontarSaldo(data.getIdCuentaUsuario());
            movimientoService.guardar(data.getIdCuentaUsuario());

            log.info("Saldo actualizado | Cuenta={}",
                    data.getIdCuentaUsuario());

        } catch (Exception e) {
            log.error("Error procesando mensaje Kafka", e);
        }
    }
    
}

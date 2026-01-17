// package com.example.MsEvaluacion.services;

// import com.example.MsEvaluacion.model.events.cursoDetalleEvent;
// import com.example.MsEvaluacion.repository.cursoRepository;
// import com.example.MsEvaluacion.model.entity.CursoModel;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// @Service
// public class EvaluacionConsumerListener {

//     @Autowired
//     private cursoRepository cursoRepo;

//     @KafkaListener(topics = "curso-detalle-events")
//     public void consumirEvento(cursoDetalleEvent evento) {

//         CursoModel nuevoCursoEvent = new CursoModel();
//         // 2. Mapear los datos del evento al modelo de datos
//         nuevoCursoEvent.setIdDetalleCurso(evento.getIdDetalleCurso());
//         nuevoCursoEvent.setCursoNombre(evento.getCursoNombre());

//         // 3. Guardar en la base de datos
//         cursoRepo.save(nuevoCursoEvent);

//         System.out.println("Guardado exitoso: " + evento.getCursoNombre());
//     }
// }
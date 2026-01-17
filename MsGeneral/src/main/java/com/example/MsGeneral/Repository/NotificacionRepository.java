package com.example.MsGeneral.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.MsGeneral.Model.Entidad.Notificacion;

@Repository
public interface NotificacionRepository extends MongoRepository<Notificacion,String> {

    List<Notificacion> findAllByOrderByFechaCreacionDesc();

    List<Notificacion> findByLeidoOrderByFechaCreacionDesc(boolean leido);

    List<Notificacion> findByIdUsuarioOrderByFechaCreacionDesc(String idUsuario);

    List<Notificacion> findByIdUsuarioAndLeidoOrderByFechaCreacionDesc(
            String idUsuario, boolean leido);

}

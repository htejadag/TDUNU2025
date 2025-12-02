package tdunu.MsTramite.model.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tramite_movimiento")
public class TramiteMovimientoModel {
    
    @Id
    private BigInteger id;

    private Date fecha_envio;

    private Date fecha_recepcion;

    private boolean estado_tramite_id = true;

    //foreingkeys
    private TramiteModel tramite_id;

    private String usuarioCreacion;

    private String usuarioModificacion;

    @CreatedDate
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaModificacion;
}

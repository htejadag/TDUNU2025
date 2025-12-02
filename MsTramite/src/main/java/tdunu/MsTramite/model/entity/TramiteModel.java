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
@Document(collection = "tramite")
public class TramiteModel {

    @Id
    private BigInteger id;

    private Integer modalidad_id;

    private boolean estado = true;

    private Date fecha_inicio;
    
    private Date fecha_fin;

    private String observaciones;

    //foreingkeys


    private String usuarioCreacion;

    private String usuarioModificacion;

    @CreatedDate
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaModificacion;
}

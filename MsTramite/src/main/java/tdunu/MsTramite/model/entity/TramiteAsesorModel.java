package tdunu.MsTramite.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tramite_asesor")
public class TramiteAsesorModel implements Serializable{

    @Id
    private BigInteger id;

    private Date fecha_designacion;

    private boolean estado = true;

    //foreingkeys
    private TramiteModel tramite_id;

    private String usuarioCreacion;

    private String usuarioModificacion;

    @CreatedDate
    private LocalDate fechaCreacion;

    @LastModifiedDate
    private LocalDate fechaModificacion;

}

package tdunu.MsTramite.model.entity;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "tramite_jurado")
public class TramiteJuradoModel {
    

    @Id
    private BigInteger id;
    
    private Integer rol_id;

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

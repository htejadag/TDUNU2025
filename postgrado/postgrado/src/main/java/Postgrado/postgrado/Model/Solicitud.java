package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "solicitudes")
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-solicitud")
    private Expediente expediente;

    private String tipoSolicitud;
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    private String estadoSolicitud;
    private String descripcion;

     @OneToMany(mappedBy = "solicitud")
    @JsonManagedReference(value = "sol-doc")
    private List<Documento> documentos;
}

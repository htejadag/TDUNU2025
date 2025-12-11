package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "solicitudes")
@Data
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private Expediente expediente;

    private String tipoSolicitud;
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    private String estadoSolicitud;
    private String descripcion;

    @OneToMany(mappedBy = "solicitud")
    private List<Documento> documentos;
}

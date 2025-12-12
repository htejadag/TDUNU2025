package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "expediente")
@Data
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpediente;

    private Integer idEstudiante;

    private String estadoExpediente;
    private LocalDateTime fechaApertura;
    private LocalDateTime fechaCierre;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_asesor")
    private Asesor asesor;

    @OneToMany(mappedBy = "expediente")
    @JsonManagedReference(value = "exp-solicitud")
    private List<Solicitud> solicitudes;

    
    @OneToMany(mappedBy = "expediente")
    @JsonManagedReference(value = "exp-tesis")
    private List<Tesis> tesis;


    @OneToMany(mappedBy = "expediente")
    private List<ExpedienteJurado> jurados;
}

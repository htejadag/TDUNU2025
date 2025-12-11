package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tesis")
@Data
public class Tesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTesis;

    @ManyToOne
    @JoinColumn(name = "id_expediente", nullable = false)
    private Expediente expediente;

    private String titulo;
    private String proyectoPdf;
    private String informeFinalPdf;
    private String antiplagioProyectoPdf;
    private String antiplagioFinalPdf;

    private String estadoProyecto;
    private String estadoInformeFinal;

    private LocalDateTime fechaRegistro = LocalDateTime.now();
}

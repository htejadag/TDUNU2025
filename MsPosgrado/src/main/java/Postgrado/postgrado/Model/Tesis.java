package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tesis")
@Data
public class Tesis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTesis;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-tesis")
    private Expediente expediente;
    
    @OneToMany(mappedBy = "tesis")
    @JsonManagedReference(value = "tesis-revision")
    private List<Revision> revisiones;

    private String titulo;
    private String proyectoPdf;
    private String informeFinalPdf;
    private String antiplagioProyectoPdf;
    private String antiplagioFinalPdf;

    private String estadoProyecto;
    private String estadoInformeFinal;

    private LocalDateTime fechaRegistro = LocalDateTime.now();
}

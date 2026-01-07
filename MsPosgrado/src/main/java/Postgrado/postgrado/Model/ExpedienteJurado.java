package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "expediente_jurado")
@Data
public class ExpedienteJurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExpJurado;

    @ManyToOne
    @JoinColumn(name = "id_expediente")
    @JsonBackReference(value = "exp-jurado")
    private Expediente expediente;

    @ManyToOne
    @JoinColumn(name = "id_jurado", nullable = false)
    private Jurado jurado;

    private String rol;

    private LocalDateTime fechaDesignacion = LocalDateTime.now();
}
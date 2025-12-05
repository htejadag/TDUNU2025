package MsGL.MS_Gestion_Legal.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "articulo_cientifico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ArticuloCientifico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlArchivo;
    private String revista;
    private String doi;
    private String estadoRevision;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

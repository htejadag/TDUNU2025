package MsGL.MS_Gestion_Legal.domain.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "diploma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diploma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroResolucion;
    private LocalDate fechaEmision;
    private String urlPdf;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

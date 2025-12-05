package MsGL.MS_Gestion_Legal.domain.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "constancias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Constancia {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoConstancia;
    private String urlArchivo;
    private LocalDate fechaPresentacion;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

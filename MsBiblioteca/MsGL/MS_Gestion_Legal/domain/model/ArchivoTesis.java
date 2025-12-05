package MsGL.MS_Gestion_Legal.domain.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "archivo_tesis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArchivoTesis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlArchivo;
    private String version;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

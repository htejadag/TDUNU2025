package MsGL.MS_Gestion_Legal.domain.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "idiomas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreIdioma;
    private String nivel;
    private String institucion;
    private String urlCertificado;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

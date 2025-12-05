package MsGL.MS_Gestion_Legal.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "certificados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Certificado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoCertificado;
    private String urlArchivo;
    private LocalDate fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "expediente_id")
    private ExpedienteFinal expedienteFinal;
}

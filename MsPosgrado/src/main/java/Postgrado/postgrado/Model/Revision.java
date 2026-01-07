package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "revision")
@Data
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevision;

    @ManyToOne
    @JoinColumn(name = "id_tesis")
    @JsonBackReference(value = "tesis-revision")
    private Tesis tesis;

    @ManyToOne
    @JoinColumn(name = "id_revisor", nullable = false)
    private Jurado jurado;  // EL JURADO REVISA SEGÚN TÚ MODELO

    private String tipoRevision;
    private String comentario;
    private String dictamen;

    private LocalDateTime fechaRevision = LocalDateTime.now();
}
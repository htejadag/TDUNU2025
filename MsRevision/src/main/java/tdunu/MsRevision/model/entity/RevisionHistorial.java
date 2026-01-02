package tdunu.MsRevision.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "revision_historial")
public class RevisionHistorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision")
    private Integer idRevision;

    @Column(name = "id_proyecto")
    private Integer idProyecto;

    @Column(name = "id_revisor_usuario")
    private Integer idRevisorUsuario;

    // Referencia lógica a Catalogo (Categoria: 'TIPO_FASE')
    @Column(name = "id_tipo_fase_cat")
    private Integer idTipoFaseCat;

    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;

    // Referencia lógica a Catalogo (Categoria: 'ESTADO_DICTAMEN')
    @Column(name = "id_estado_dictamen_cat")
    private Integer idEstadoDictamenCat;

    @Column(name = "intento_numero")
    private Integer intentoNumero;

    @Column(name = "fecha_revision")
    private LocalDateTime fechaRevision;

    @Column(name = "fecha_limite_atencion")
    private LocalDateTime fechaLimiteAtencion;

    @PrePersist
    public void prePersist() {
        if (this.fechaRevision == null) {
            this.fechaRevision = LocalDateTime.now();
        }
        if (this.intentoNumero == null) {
            this.intentoNumero = 1;
        }
    }
}
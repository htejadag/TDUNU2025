package tdunu.MsTitulacion.model.entity;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "revision_borrador")
@NoArgsConstructor
@AllArgsConstructor
public class RevisionBorrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision_borrador")
    private int idRevisionBorrador;
    @Column(name = "id_jurado")private int idJurado;
    @Column(name = "id_tesis_borrador")private int idTesisBorrador;
    @Column(name = "comentarios")private String comentarios;
    @Column(name = "aprobado")private boolean aprobado;
    @Column(name = "fecha_revision")private LocalDateTime fechaRevision;


}

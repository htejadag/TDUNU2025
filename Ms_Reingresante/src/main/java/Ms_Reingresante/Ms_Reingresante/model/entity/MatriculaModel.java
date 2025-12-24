package Ms_Reingresante.Ms_Reingresante.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "matricula")
public class MatriculaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Matricula")
    private Integer idMatricula;
    
    @Column(name = "id_Proceso")
    private Integer idProceso;
    
    @Column(name = "id_Resolucion")
    private Integer idResolucion;
    
    @Column(name = "Mat_Fecha")
    private LocalDate matFecha;
    
    @Column(name = "Mat_Realizado_Por", length = 50)
    private String realizadoPor;
    
    // Campos de Auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    
    @Column(name = "USUARIO_CREACION", length = 50)
    private String usuarioCreacion;
    
    @Column(name = "FECHA_MODIFICATION")
    private LocalDateTime fechaModificacion;
    
    @Column(name = "USUARIO_MODIFICATION", length = 50)
    private String usuarioModificacion;
}
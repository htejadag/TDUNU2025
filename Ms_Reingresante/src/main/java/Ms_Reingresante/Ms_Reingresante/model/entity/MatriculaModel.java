package Ms_Reingresante.Ms_Reingresante.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "matricula")
public class MatriculaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Matricula")
    private Integer idMatricula;

    public Integer getIdMatricula() {
        return this.idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }
    
    @Column(name = "id_Proceso")
    private Integer idProceso;

    public Integer getIdProceso() {
        return this.idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }
    
    @Column(name = "id_Resolucion")
    private Integer idResolucion;

    public Integer getIdResolucion() {
        return this.idResolucion;
    }

    public void setIdResolucion(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }
    
    @Column(name = "Mat_Fecha")
    private LocalDate matFecha;
    
    @Column(name = "Mat_Realizado_Por", length = 50)
    private String realizadoPor;
    
    // Campos de Auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;
    
    @Column(name = "USUARIO_CREACION", length = 50)
    private String usuarioCreacion;
    
    @Column(name = "FECHA_MODIFICATION")
    private LocalDate fechaModificacion;
    
    @Column(name = "USUARIO_MODIFICATION", length = 50)
    private String usuarioModificacion;

 
}
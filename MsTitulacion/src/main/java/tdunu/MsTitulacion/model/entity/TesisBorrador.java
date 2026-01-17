package tdunu.MsTitulacion.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tesis_borrador")
public class TesisBorrador {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tesis_borrador")
    private int idTesisBorrador ;

    @Column(name = "id_proyecto")private int idProyecto;
    @Column(name = "ruta_constancia_coti")private String rutaConstanciaCoti;
    @Column(name = "ruta_borrador")private String rutaBorrador;
    @Column(name = "estado_borrador")private int estadoBorrador;
    @Column(name = "fecha_subida")private LocalDateTime fechaSubida;

      @PrePersist
    protected void onCreate() {this.fechaSubida = LocalDateTime.now();}

    // Se ejecuta automáticamente al ACTUALIZAR un registro existente
    @PreUpdate
    protected void onUpdate() {this.fechaSubida = LocalDateTime.now();}

}

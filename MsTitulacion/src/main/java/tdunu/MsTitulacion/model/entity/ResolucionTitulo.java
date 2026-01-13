package tdunu.MsTitulacion.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "resolucion_titulo")
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionTitulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resolucion")
    private int idResolucion;
    @Column(name = "id_dictamen") private int idDictamen;
    @Column(name = "numero_resolucion") private String numeroResolucion;
    @Column(name = "fecha_emision") private LocalDateTime fechaEmision;
    @Column(name = "ruta_pdf_titulo") private String rutaPdfTitulo;
    @Column(name = "registrado_sunedo") private boolean registradoPorSunedo;
    

    @PrePersist
    protected void onCreate() {this.fechaEmision = LocalDateTime.now();}

    // Se ejecuta automáticamente al ACTUALIZAR un registro existente
    @PreUpdate
    protected void onUpdate() {this.fechaEmision = LocalDateTime.now();}

}

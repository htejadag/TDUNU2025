package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "detalleLibro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleLibro implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column (name = "id_detalle_libro")
    private Long idDetalleLibro;

    //-- instanciando Libro--//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro", nullable = false) 
    @ToString.Exclude 
    private Libro libro;

    @Column(nullable = false)
    @Min(value = 0, message = "El valor no puede ser negativo")
    private Integer stockTotal;

    @Column(nullable = false)
    private Integer stockDisponible;

    @Column(nullable = false, length=150)
    private String ubicacionFisica;

    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist(){
    this.fechaActualizacion = LocalDateTime.now();
    if (this.stockDisponible == null){
        this.stockDisponible = this.stockTotal;
    }
    }

    @PreUpdate
    public void actualizarFecha() {
        fechaActualizacion = LocalDateTime.now();
    }
}

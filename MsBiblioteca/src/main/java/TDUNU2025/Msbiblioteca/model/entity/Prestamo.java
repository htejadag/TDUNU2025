package tdunu2025.msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prestamo") 
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded=true) 
public class Prestamo implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo")
    @EqualsAndHashCode.Include
    private Long idPrestamo;

    @Column(name = "id_usuario" ,nullable = false)
    private Long idUsuario;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detalle_libro", nullable = false) 
    @ToString.Exclude 
    private DetalleLibro detalleLibro;

    @Column(name = "fecha_prestamo",nullable = false, updatable = false)
    private LocalDateTime fechaPrestamo;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "fecha_devolucion")
    private LocalDateTime fechaDevolucion;

    @Column(name = "id_estado_prestamo",nullable = false)
    private Long idEstadoPrestamo; 

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @PrePersist
    public void prePersist(){
        if (this.fechaPrestamo == null){
            this.fechaPrestamo = LocalDateTime.now();   
        }
        if (this.fechaVencimiento == null){
            this.fechaVencimiento = LocalDate.now().plusDays(7);
        }

        if (this.idEstadoPrestamo == null){
            this.idEstadoPrestamo = 1L;
        }

    }
}
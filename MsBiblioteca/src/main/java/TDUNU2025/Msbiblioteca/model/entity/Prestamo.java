package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrestamo;

    // --- CLAVES FORÁNEAS (Tratadas como campos simples) ---
    @Column(nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private Long idDetalleLibro;

    // --- FECHAS ---
    // Usamos LocalDate, JPA lo mapea automáticamente a DATE en SQL
    @Column(nullable = false)
    private LocalDate fechaPrestamo;

    @Column(nullable = false)
    private LocalDate fechaVencimiento;

    // Puede ser nulo porque al crear el préstamo aún no se devuelve
    private LocalDate fechaDevolucion;

    // --- OTROS CAMPOS ---
    @Column(nullable = false)
    private Long idEstadoPrestamo; // 1: Pendiente, 2: Devuelto, etc.

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDetalleLibro",nullable = false, insertable = false, updatable = false)
    @ToString.Exclude
    private DetalleLibro detalleLibro;
}
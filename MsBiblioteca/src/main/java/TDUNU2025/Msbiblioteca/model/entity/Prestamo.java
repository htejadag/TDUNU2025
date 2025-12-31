package TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "prestamo") // Nombre exacto de la tabla en BD
@Data // Lombok genera Getters, Setters, toString, etc.
@NoArgsConstructor // Constructor vacío (Obligatorio para JPA)
@AllArgsConstructor // Constructor con argumentos
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrestamo;

    // --- CLAVES FORÁNEAS (Tratadas como campos simples) ---
    @Column(nullable = false)
    private Integer idUsuario;

    @Column(nullable = false)
    private Integer idLibro;

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
    private Integer idEstadoPrestamo; // 1: Pendiente, 2: Devuelto, etc.

    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
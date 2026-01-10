<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.entity;
=======
package TDUNU2025.Msbiblioteca.model.entity;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "detalle_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleUsuario {

    @Id
    private Integer idUsuario; // Definido como PK según tu instrucción

    @Column(nullable = false)
    private Long idDetalleUsuario; // Definido como FK según tu instrucción

    private Integer totalPrestamos;

    private double totalMultas;

    private LocalDate fechaUltimoPrestamo;
}
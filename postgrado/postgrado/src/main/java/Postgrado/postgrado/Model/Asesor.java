package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "asesor")
@Data
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsesor;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 120, message = "Los apellidos no pueden superar los 120 caracteres")
    private String apellidos;

    @NotBlank(message = "El grado máximo es obligatorio")
    @Size(max = 50, message = "El grado máximo no puede superar los 50 caracteres")
    private String gradoMaximo;

    @NotBlank(message = "La ruta del CV es obligatoria")
    @Size(max = 255, message = "La ruta del CV no puede superar los 255 caracteres")
    private String cvRuta;

    @NotBlank(message = "La ruta de la declaración jurada es obligatoria")
    @Size(max = 255, message = "La ruta de la declaración no puede superar los 255 caracteres")
    private String declaracionRuta;

    @NotBlank(message = "El estado del asesor es obligatorio")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estadoAsesor;

    // Se ejecuta automáticamente al crear el registro y considera "ACTIVO" al nuevo asesor por defecto
    @PrePersist
    public void prePersist() {
        this.estadoAsesor = "ACTIVO";
    }
}

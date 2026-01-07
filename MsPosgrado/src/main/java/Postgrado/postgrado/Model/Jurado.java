package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "jurado")
@Data
public class Jurado extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJurado;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 120, message = "Los apellidos no pueden superar los 120 caracteres")
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 150, message = "La especialidad no puede superar los 150 caracteres")
    private String especialidad;

    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estadoJurado;

    @PrePersist
    public void prePersist() {
        if (this.estadoJurado == null) {
            this.estadoJurado = "ACTIVO";
        }
    }
}
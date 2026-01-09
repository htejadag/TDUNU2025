package msposgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "jurado")
@Schema(
    description = "Entidad que representa a un jurado académico que participa en la evaluación de expedientes"
)
public class Jurado extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador único del jurado",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Integer idJurado;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Los nombres no pueden superar los 100 caracteres")
    @Schema(
        description = "Nombres del jurado",
        example = "Carlos Alberto"
    )
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 120, message = "Los apellidos no pueden superar los 120 caracteres")
    @Schema(
        description = "Apellidos del jurado",
        example = "Pérez Gómez"
    )
    private String apellidos;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(max = 150, message = "La especialidad no puede superar los 150 caracteres")
    @Schema(
        description = "Especialidad académica del jurado",
        example = "Ingeniería de Sistemas"
    )
    private String especialidad;

    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    @Schema(
        description = "Estado actual del jurado",
        example = "ACTIVO"
    )
    private String estadoJurado;

    /**
     * Asigna el estado ACTIVO al jurado al momento de su registro,
     * si no se ha definido previamente.
     */
    @PrePersist
    public void prePersist() {
        if (this.estadoJurado == null) {
            this.estadoJurado = "ACTIVO";
        }
    }

    public Integer getIdJurado() {
        return idJurado;
    }

    public void setIdJurado(Integer idJurado) {
        this.idJurado = idJurado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEstadoJurado() {
        return estadoJurado;
    }

    public void setEstadoJurado(String estadoJurado) {
        this.estadoJurado = estadoJurado;
    }
}
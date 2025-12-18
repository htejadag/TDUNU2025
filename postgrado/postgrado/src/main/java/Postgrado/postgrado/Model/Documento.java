package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "documentos")
@Data
@EqualsAndHashCode(callSuper = false)
public class Documento extends AuditoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDocumento;

    @ManyToOne
    @JoinColumn(name = "id_solicitud")
    @JsonBackReference(value = "sol-doc")
    @NotNull(message = "La solicitud es obligatoria")
    private Solicitud solicitud;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Size(max = 50, message = "El tipo de documento no puede superar los 50 caracteres")
    private String tipoDocumento;

    @NotBlank(message = "La ruta del archivo es obligatoria")
    @Size(max = 255, message = "La ruta del archivo no puede superar los 255 caracteres")
    private String archivoRuta;

    // Fecha documento eliminada, se usa auditoría
}

package unu.MsGestionDocumental.MsExpediente.model.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Data

public class requestExpediente {
    
    @NotBlank(message = "El asunto es obligatorio")
    @Size(max = 255, message = "El asunto no debe de pasar los 100 caracteres")
    public String asunto;

    @NotNull(message = "El usuario es obligatorio")
    public Integer usuarioId;

    public LocalDateTime fechaEdicion;

    public Integer creadoPor;    

    public LocalDateTime fechaIngreso;

    public boolean activo = true;
    
}
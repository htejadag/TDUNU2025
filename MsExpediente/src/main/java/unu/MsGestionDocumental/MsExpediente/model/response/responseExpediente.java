package main.java.unu.MsGestionDocumental.MsExpediente.model.response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseExpediente {
        
    public String correlativo;
    public String asunto;    
    public Integer usuarioID;
    private LocalDateTime fechaIngreso;    
    private LocalDateTime fechaCreacion;
    private Integer creadoPor;
    private boolean activo;
}
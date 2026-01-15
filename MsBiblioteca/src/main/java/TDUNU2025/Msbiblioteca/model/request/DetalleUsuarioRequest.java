    package tdunu2025.msbiblioteca.model.request;

    import lombok.Data;

    import jakarta.validation.constraints.NotNull;

    @Data
    public class DetalleUsuarioRequest {
        
        @NotNull(message = "el Id del usuario es obligatgorio")
        private Long idUsuario;

    }
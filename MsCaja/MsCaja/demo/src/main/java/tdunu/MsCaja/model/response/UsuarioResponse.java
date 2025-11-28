package tdunu.MsCaja.model.response;

public class UsuarioResponse {
    private Integer idUsuario;
    private String codigo;

    public UsuarioResponse(Integer idUsuario, String codigo) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
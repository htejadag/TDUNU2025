package tdunu.MsCaja.model.response;

public class TipoDocumentoResponse {
    private Integer idTipoDocumento;
    private String nombreDocumento;

    public TipoDocumentoResponse(Integer idTipoDocumento, String nombreDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreDocumento = nombreDocumento;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
}
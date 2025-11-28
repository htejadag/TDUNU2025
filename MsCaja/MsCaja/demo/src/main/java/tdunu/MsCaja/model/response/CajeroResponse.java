package tdunu.MsCaja.model.response;

import tdunu.MsCaja.model.entity.Cajero;
import java.util.List;
import java.util.stream.Collectors;

public class CajeroResponse {
    private Integer idCajero;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nroDocumento;
    private String codigoEmpleado;
    private Integer idTipoDocumento;
    private Integer idUsuario;

    // Getters and Setters

    public Integer getIdCajero() {
        return idCajero;
    }
    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }
    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Add constructors and helper methods for mapping
    public CajeroResponse(Cajero entity) {
        this.idCajero = entity.getIdCajero();
        this.nombre = entity.getNombre();
        this.apellidoPaterno = entity.getApellidoPaterno();
        this.apellidoMaterno = entity.getApellidoMaterno();
        this.nroDocumento = entity.getNroDocumento();
        this.codigoEmpleado = entity.getCodigoEmpleado();
        this.idTipoDocumento = entity.getTipoDocumento() != null ? entity.getTipoDocumento().getIdTipoDocumento() : null;
        this.idUsuario = entity.getUsuario() != null ? entity.getUsuario().getIdUsuario() : null;
    }

    public static List<CajeroResponse> fromEntityList(List<Cajero> entities) {
        return entities.stream().map(CajeroResponse::new).collect(Collectors.toList());
    }
}
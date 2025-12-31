package TDUNU2025.Msbiblioteca.util;

import lombok.AllArgsConstructor;
import lombok.Builder; 
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private String codigo;
    private String mensaje;
    private T data;

    public ResponseBase(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.data = null;
    }
}
<<<<<<< HEAD
package tdunu2025.msbiblioteca.util;
=======
package TDUNU2025.Msbiblioteca.util;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase<T> {
    
    private String codigo;
    private String mensaje;
    private T data;
    
    // Constructor auxiliar para respuestas exitosas rápidas
    public ResponseBase(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
}
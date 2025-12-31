package TDUNU2025.Msbiblioteca.util;

public class Mensaje {

    private Mensaje() {
        throw new IllegalStateException("Utility class");
    }

    public static final String CODE_OK = "200";

    public static final String CODE_ERROR = "500"; 

    public static final String CODE_NO_ENCONTRADO = "404"; 
    
    public static final String MENSAJE_EXITO = "Operación realizada con éxito";
    public static final String MENSAJE_NO_ENCONTRADO = "El registro no fue encontrado";
    public static final String MENSAJE_GUARDADO = "Se guardó el registro correctamente";
    public static final String MENSAJE_ACTUALIZADO = "Se actualizó el registro correctamente";
    public static final String MENSAJE_ELIMINADO = "Se eliminó el registro correctamente";
}
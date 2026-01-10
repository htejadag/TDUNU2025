<<<<<<< HEAD
package tdunu2025.msbiblioteca.model.request;
=======
package TDUNU2025.Msbiblioteca.model.request;
>>>>>>> parent of 852a09b (Actualizacion de ruta)

import lombok.Data;

@Data
public class CatalogoRequest {
    // No incluimos idCatalogo porque es autogenerado por la base de datos
    private String nombre;
    private String descripcion;
    private Integer estado; // 1: Activo, 0: Inactivo
}
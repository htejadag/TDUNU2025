package com.unu.TDUNU2025.Msbiblioteca.model.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EditorialRequest {
    
    private String nombre;

    private String direccion; // Si decides usar ID de tabla externa, cambia esto a Long idDireccion

    private String telefono;

    private String email;

    private String sitioWeb;

    private String pais;
}

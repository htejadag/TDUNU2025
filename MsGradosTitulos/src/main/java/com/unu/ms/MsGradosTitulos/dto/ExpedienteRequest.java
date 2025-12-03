package com.unu.ms.MsGradosTitulos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
public class ExpedienteRequest {
    private Integer idExpediente; // Este campo no se recibe en la creación, pero lo dejamos.
    
    @JsonProperty("id_estado") // JSON trae id_estado
    private Integer idEstado;
    
    @JsonProperty("id_persona") // JSON trae id_persona
    private Integer idPersona;
    
    @JsonProperty("id_usuario_creo") // JSON trae id_usuario_creo
    private Integer idUsuarioCreo;
    
    @JsonProperty("codigo_expediente") // JSON trae codigo_expediente
    private String codigoExpediente;
    
    // Este campo tiene el mismo nombre, por lo que no es estrictamente necesario, pero lo incluimos
    @JsonProperty("descripcion") 
    private String descripcion;
    
    @JsonProperty("fecha_apertura") // JSON trae fecha_apertura
    private Date fechaApertura;
    
    @JsonProperty("fecha_cierre") // JSON trae fecha_cierre
    private Date fechaCierre;


}

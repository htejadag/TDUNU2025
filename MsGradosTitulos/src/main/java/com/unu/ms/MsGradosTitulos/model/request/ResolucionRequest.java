package com.unu.ms.MsGradosTitulos.model.request;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.unu.ms.MsGradosTitulos.model.entity.ExpedienteModel;
import com.unu.ms.MsGradosTitulos.model.entity.ResolucionArticuloModel;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResolucionRequest {
    
    private Integer id;
    private String numeroResolucion;
    // private ExpedienteModel expediente;
    private Integer idSolicitud;
    private Integer idEstado;
    private Integer idTipoResolucion;
    private LocalDate fechaEmision;
    private String resumen;
    private String fundamento;
    private String articuladoGeneral;
    private Boolean aprobadoEnSesion;
    private Integer usuarioCreo;
    // private Timestamp fechaCreacion;
    // private List<ResolucionArticuloModel> articulos;
}

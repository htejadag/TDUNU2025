package Ms_Reingresante.Ms_Reingresante.model.entity;


import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "resolucion")
public class ResolucionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Resolucion")
    private Integer idResolucion;

    @Column(name = "Res_Numero")
    private String resNumero;

    @Column(name = "Res_Codigo_Activacion")
    private String resCodigoActivacion;

    @Column(name = "Res_Fecha_Emision")
    private LocalDate resFechaEmision;  

    @Column(name = "Res_Emitido_Por")
    private String resEmitidoPor;

    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion;   // DATETIME SQL

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion;

    @Column(name = "FECHA_MODIFICACION")
    private LocalDate fechaModificacion;   // DATETIME SQL

    @Column(name = "USUARIO_MODIFICACION")
    private String usuarioModificacion;

}


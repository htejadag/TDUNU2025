package com.unu.ms.MsGradosTitulos.model;

import java.util.Date;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "seguimiento")
public class Seguimiento {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSeguimiento;

    private int idEntidadCatalogo;

    private int entidadId;

    private int idEstado;

    private String comentario;

    private int idUsuario;

    private Date fechaRegistro;
}

package com.unu.ms.MsConsejo.model.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@MappedSuperclass // Indica que esta clase no es una tabla, sino una plantilla para otras
@EntityListeners(AuditingEntityListener.class) // Activa el "escucha" automático de eventos
public abstract class AuditoriaModel {

    @CreatedDate
    @Column(name = "au_fechacr", updatable = false) // No se debe actualizar nunca
    private Date auFechacr;

    @LastModifiedDate
    @Column(name = "au_fechamd")
    private Date auFechamd;

    // OJO: Spring no tiene una anotación automática para "DeletedDate", 
    // este lo manejaremos manualmente para el Borrado Lógico.
    @Column(name = "au_fechael")
    private Date auFechael;

    @CreatedBy // Spring intentará llenar esto con el ID del usuario logueado
    @Column(name = "au_usuariocr", updatable = false)
    private Integer auUsuariocr;

    @LastModifiedBy // Spring llenará esto al editar
    @Column(name = "au_usuariomd")
    private Integer auUsuariomd;

    @Column(name = "au_usuarioel")
    private Integer auUsuarioel;

    // Getters y Setters
    public Date getAuFechacr() { return auFechacr; }
    public void setAuFechacr(Date auFechacr) { this.auFechacr = auFechacr; }
    
    public Date getAuFechamd() { return auFechamd; }
    public void setAuFechamd(Date auFechamd) { this.auFechamd = auFechamd; }

    public Date getAuFechael() { return auFechael; }
    public void setAuFechael(Date auFechael) { this.auFechael = auFechael; }

    public Integer getAuUsuariocr() { return auUsuariocr; }
    public void setAuUsuariocr(Integer auUsuariocr) { this.auUsuariocr = auUsuariocr; }

    public Integer getAuUsuariomd() { return auUsuariomd; }
    public void setAuUsuariomd(Integer auUsuariomd) { this.auUsuariomd = auUsuariomd; }
    
    public Integer getAuUsuarioel() { return auUsuarioel; }
    public void setAuUsuarioel(Integer auUsuarioel) { this.auUsuarioel = auUsuarioel; }
}

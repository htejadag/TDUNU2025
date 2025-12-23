package com.service.MsTramiteTesis.model.dto;

public class RevisionRequest {

    private Boolean aprobado;
    private String observaciones;

    // Constructors
    public RevisionRequest() {
    }

    public RevisionRequest(Boolean aprobado, String observaciones) {
        this.aprobado = aprobado;
        this.observaciones = observaciones;
    }

    // Getters and Setters
    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}

package com.example.mscursos.dto;

import java.time.Instant;

public class CursoEvent {
    private String type; // "UPSERT" o "DELETE"
    private Instant occurredAt;
    private CursoPayload curso;

    public CursoEvent() {
    }

    public CursoEvent(String type, Instant occurredAt, CursoPayload curso) {
        this.type = type;
        this.occurredAt = occurredAt;
        this.curso = curso;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }

    public CursoPayload getCurso() {
        return curso;
    }

    public void setCurso(CursoPayload curso) {
        this.curso = curso;
    }
}

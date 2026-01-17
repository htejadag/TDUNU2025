package com.example.mscursos.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CursoEvent {
    private String type; // "UPSERT" o "DELETE"
    private Instant occurredAt;
    private CursoPayload curso;

}

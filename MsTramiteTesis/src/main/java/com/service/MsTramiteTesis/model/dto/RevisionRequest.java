package com.service.MsTramiteTesis.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevisionRequest {

    private Boolean aprobado;
    private String observaciones;

}
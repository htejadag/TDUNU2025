package com.example.MsPlanEstudios.model.response;

import java.util.List;

import lombok.Data;

@Data
public class MallaPlanResponse {
    private String plan; // e.g. año o código del plan
    private List<CicloMallaResponse> ciclos;
}
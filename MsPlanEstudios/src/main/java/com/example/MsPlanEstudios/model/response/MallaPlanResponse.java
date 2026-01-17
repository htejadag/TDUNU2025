package com.example.MsPlanEstudios.model.response;

import java.util.List;

import lombok.Data;

@Data
public class MallaPlanResponse {
    private String plan; // año o nombre del plan
    private List<CicloMallaResponse> ciclos;
}
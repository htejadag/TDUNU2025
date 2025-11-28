package com.unu.ms.MsConsejo.model.response;

import java.sql.Timestamp;
import java.util.List;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsejoResponse {
    
    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer estado;
    private Timestamp fechaCreacion;
    private List<MiembroConsejoModel> miembros;
    private List<SesionConsejoModel> sesiones;
    
}
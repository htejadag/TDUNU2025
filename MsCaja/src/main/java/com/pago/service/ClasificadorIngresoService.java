package com.pago.service;

import java.util.List;
import com.pago.model.entity.ClasificadorIngresoModel;

public interface ClasificadorIngresoService {

    public abstract List<ClasificadorIngresoModel> listarClasificadorIngreso();

    public abstract ClasificadorIngresoModel obtenerClasificadorIngreso(int id);

    public abstract ClasificadorIngresoModel registrarClasificadorIngreso(ClasificadorIngresoModel clasificadorIngreso);

    public abstract ClasificadorIngresoModel actualizarClasificadorIngreso(ClasificadorIngresoModel clasificadorIngreso);

    public abstract void desactivarClasificadorIngreso(int id);

    public abstract void eliminarClasificadorIngreso(int id);
}

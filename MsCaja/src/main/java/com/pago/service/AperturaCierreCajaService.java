package com.pago.service;

import java.util.List;
import com.pago.model.entity.AperturaCierreCajaModel;

public interface AperturaCierreCajaService {

    public abstract List<AperturaCierreCajaModel> listarApeCieCaja();

    public abstract AperturaCierreCajaModel obtenerApeCieCaja(int id);

    public abstract AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel ape_cie_caja);

    public abstract AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel ape_cie_caja);

    public abstract void desactivarApeCieCaja(int id);

    public abstract void eliminarApeCieCaja(int id);

}

package com.pago.service.Imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pago.model.entity.AperturaCierreCajaModel;
import com.pago.repository.AperturaCierreCajaRepository;
import com.pago.service.AperturaCierreCajaService;

@Service("ape_cie_cajaServicio")
public class AperturaCierreCajaServiceImp implements AperturaCierreCajaService {
    @Autowired
    @Qualifier("ape_cie_cajaRepositorio")
    private AperturaCierreCajaRepository ape_cie_cajaRepositorio;

    @Override
    public List<AperturaCierreCajaModel> listarApeCieCaja() {
        return ape_cie_cajaRepositorio.findAll();
    }

    @Override
    public AperturaCierreCajaModel obtenerApeCieCaja(int id) {
        return ape_cie_cajaRepositorio.findById(id).orElse(null);
    }

    @Override
    public AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel ape_cie_caja) {
        return ape_cie_cajaRepositorio.save(ape_cie_caja);
    }

    @Override
    public AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel ape_cie_caja) {
        return ape_cie_cajaRepositorio.save(ape_cie_caja);
    }

    @Override
    public void desactivarApeCieCaja(int id) {
        ape_cie_cajaRepositorio.desactivar(id);
    }

    @Override
    public void eliminarApeCieCaja(int id) {
        ape_cie_cajaRepositorio.eliminar(id);
    }
    
}

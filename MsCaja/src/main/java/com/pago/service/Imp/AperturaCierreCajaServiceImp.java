package com.pago.service.Imp;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pago.model.entity.AperturaCierreCajaModel;
import com.pago.repository.AperturaCierreCajaRepository;
import com.pago.service.AperturaCierreCajaService;

@Slf4j
@Service("ape_cie_cajaServicio")
public class AperturaCierreCajaServiceImp implements AperturaCierreCajaService {
    @Autowired
    @Qualifier("ape_cie_cajaRepositorio")
    private AperturaCierreCajaRepository ape_cie_cajaRepositorio;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AperturaCierreCajaModel> listarApeCieCaja() {
        return ape_cie_cajaRepositorio.findAll().
        stream()
        .map(model -> modelMapper.map(model, AperturaCierreCajaModel.class))
        .toList();
    }

    @Override
    public AperturaCierreCajaModel obtenerApeCieCaja(int id) {
        return ape_cie_cajaRepositorio.findById(id)
        .map(model -> modelMapper.map(model, AperturaCierreCajaModel.class))
        .orElse(null);
    }

    @Override
    public AperturaCierreCajaModel registrarApeCieCaja(AperturaCierreCajaModel ape_cie_caja) {
        AperturaCierreCajaModel model = modelMapper.map(ape_cie_caja, AperturaCierreCajaModel.class);
        AperturaCierreCajaModel saved = ape_cie_cajaRepositorio.save(model);
        return modelMapper.map(saved, AperturaCierreCajaModel.class);
    }

    @Override
    public AperturaCierreCajaModel actualizarApeCieCaja(AperturaCierreCajaModel ape_cie_caja) {
        AperturaCierreCajaModel model = modelMapper.map(ape_cie_caja, AperturaCierreCajaModel.class);
        AperturaCierreCajaModel saved = ape_cie_cajaRepositorio.save(model);
        return modelMapper.map(saved, AperturaCierreCajaModel.class);
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

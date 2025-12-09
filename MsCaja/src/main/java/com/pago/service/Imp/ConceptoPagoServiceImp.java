package com.pago.service.Imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pago.model.entity.ConceptoPagoModel;
import com.pago.repository.ConceptoPagoRepository;
import com.pago.service.ConceptoPagoService;

@Service("concepto_pagoServicio")
public class ConceptoPagoServiceImp implements ConceptoPagoService {
    @Autowired
    @Qualifier("concepto_pagoRepositorio")
    private ConceptoPagoRepository concepto_pagoRepositorio;

    @Override
    public List<ConceptoPagoModel> listarConceptoPago() {
        return concepto_pagoRepositorio.findAll();
    }

    @Override
    public ConceptoPagoModel obtenerConceptoPago(int id) {
        return concepto_pagoRepositorio.findById(id).orElse(null);
    }

    @Override
    public ConceptoPagoModel registrarConceptoPago(ConceptoPagoModel ape_cie_caja) {
        return concepto_pagoRepositorio.save(ape_cie_caja);
    }

    @Override
    public ConceptoPagoModel actualizarConceptoPago(ConceptoPagoModel ape_cie_caja) {
        return concepto_pagoRepositorio.save(ape_cie_caja);
    }

    @Override
    public void desactivarConceptoPago(int id) {
        concepto_pagoRepositorio.desactivar(id);
    }

    @Override
    public void eliminarConceptoPago(int id) {
        concepto_pagoRepositorio.eliminar(id);
    }

}

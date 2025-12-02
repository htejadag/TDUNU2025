package com.example.demo.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.AperturaCierreCaja;
import com.example.demo.Repository.AperturaCierreCajaRepository;

@Service
public class AperturaCierreCajaService {
    private static final Logger logger = LoggerFactory.getLogger(AperturaCierreCajaService.class);

    @Autowired
    private AperturaCierreCajaRepository ape_cie_cajaRepo;

    public List<AperturaCierreCaja> listarApertura() {
        return ape_cie_cajaRepo.findAll();
    }

    public AperturaCierreCaja guardarApertura(AperturaCierreCaja a) {
        logger.info("Inicio de la agregación de una nueva operación: {}", a);
        AperturaCierreCaja accGuardada = ape_cie_cajaRepo.save(a);
        if (accGuardada != null) {
            logger.info("Operación agregada exitosamente: {}", accGuardada.getIdAperturaCierreCaja());
        } else {
            logger.error("Error al agregar la operación");
        }
        return accGuardada;
    }

    public void actualizarApertura(AperturaCierreCaja a) {
        if (a.getIdAperturaCierreCaja() == null) {
            logger.error("No se puede actualizar: el ID es nulo");
            return;
        }
        if (!ape_cie_cajaRepo.existsById(a.getIdAperturaCierreCaja())) {
            logger.warn("Intento de actualizar un registro que no existe: {}", a.getIdAperturaCierreCaja());
            return;
        }
        ape_cie_cajaRepo.save(a);
        logger.info("Apertura ID {} actualizada correctamente", a.getIdAperturaCierreCaja());
    }

    public AperturaCierreCaja buscarAperturaPorId(int id) {
        return ape_cie_cajaRepo.findById(id).orElse(null);
    }

    public void eliminarApertura(int id) {
        if (!ape_cie_cajaRepo.existsById(id)) {
            logger.warn("Intento de eliminar un registro que no existe: {}", id);
            return;
        }
        ape_cie_cajaRepo.deleteById(id);
        logger.info("Registro eliminado correctamente: {}", id);
    }

}

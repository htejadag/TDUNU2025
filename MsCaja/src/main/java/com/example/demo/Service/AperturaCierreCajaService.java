package com.example.demo.Service;

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

    public AperturaCierreCaja guardarApertura(AperturaCierreCaja a) {
        logger.info("Inicio de la agregación de una nueva operación: {}", a);
        AperturaCierreCaja accGuardada = ape_cie_cajaRepo.save(a);
        if (accGuardada != null) {
            logger.info("Operación agregada exitosamente: {}", accGuardada.getIdAperturaCierreCaja());
        } else {
            // logger.error("Error al agregar la operación: {}",
            // accGuardada.getIdAperturaCierreCaja());
        }
        return accGuardada;
    }

}

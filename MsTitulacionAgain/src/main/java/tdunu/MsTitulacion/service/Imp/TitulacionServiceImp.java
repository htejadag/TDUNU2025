package tdunu.MsTitulacion.service.Imp;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tdunu.MsTitulacion.model.entity.ActaAprobacion;
import tdunu.MsTitulacion.model.entity.Catalogo;
import tdunu.MsTitulacion.model.entity.DictamenPostgrado;
import tdunu.MsTitulacion.model.request.DictamenPostgradoRequest; 
import tdunu.MsTitulacion.model.response.ActaAprobacionResponse;
import tdunu.MsTitulacion.repository.ActaRepository;
import tdunu.MsTitulacion.repository.CatalogoRepository; 
import tdunu.MsTitulacion.repository.DictamenRepository; 
import tdunu.MsTitulacion.service.TitulacionService;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class TitulacionServiceImp implements TitulacionService {

    @Autowired
    private DictamenRepository dictamenRepository;

    @Autowired
    private ActaRepository actaRepository;

    @Autowired
    private CatalogoRepository catalogoRepository; 

    @Autowired
    private ModelMapper modelMapper;

    // Constante para evitar "Magic Strings" en el código
    private static final String CODIGO_APROBADO_UNANIME = "RES_APROB_UNANIME"; 

    @Override
    @Transactional
    public ActaAprobacionResponse procesarDictamenYGenerarActa(DictamenPostgradoRequest request) {
        log.info("Procesando dictamen para el borrador ID: {}", request.getIdBorrador());

        DictamenPostgrado dictamen = new DictamenPostgrado();
        dictamen.setIdBorrador(request.getIdBorrador());
        dictamen.setFechaProgramada(request.getFechaProgramada());
        dictamen.setHoraProgramada(request.getHoraProgramada());
        dictamen.setAulaVirtualFisica(request.getAulaVirtualFisica());
        
        dictamen.setIdModalidadCat(request.getIdModalidadCat());
        dictamen.setIdResultadoFinalCat(request.getIdResultadoFinalCat());

        Catalogo resultadoCat = catalogoRepository.findById(request.getIdResultadoFinalCat())
                .orElseThrow(() -> new RuntimeException("El ID de resultado no existe en el catálogo"));

        // Guardamos el dictamen primero
        dictamenRepository.save(dictamen);

        if (CODIGO_APROBADO_UNANIME.equals(resultadoCat.getCodigo())) {
            
            log.info("Resultado '{}' detectado. Generando Acta...", resultadoCat.getValor());

            ActaAprobacion acta = new ActaAprobacion();
            acta.setIdProyecto(request.getIdBorrador()); 

            String codigoGenerado = LocalDateTime.now().getYear() + "-" + 
                                    UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            
            acta.setCodigoUnicoActa(codigoGenerado);
            acta.setFechaEmision(LocalDateTime.now());
            // Simulación de ruta
            acta.setRutaPdfFirmado("https://storage.unu.edu.pe/actas/acta_" + codigoGenerado + ".pdf");

            ActaAprobacion savedActa = actaRepository.save(acta);
            log.info("¡Acta {} generada exitosamente!", codigoGenerado);
            
            return modelMapper.map(savedActa, ActaAprobacionResponse.class);
        }

        log.info("Dictamen registrado con resultado: {}. No se genera acta.", resultadoCat.getValor());
        return null;
    }

    @Override
    public ActaAprobacionResponse obtenerActaPorProyecto(Integer idProyecto) {
        return actaRepository.findByIdProyecto(idProyecto) 
                .map(acta -> modelMapper.map(acta, ActaAprobacionResponse.class))
                .orElse(null);
    }
}
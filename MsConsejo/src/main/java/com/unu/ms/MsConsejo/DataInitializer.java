package com.unu.ms.MsConsejo;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.unu.ms.MsConsejo.model.entity.AsistenciaSesionModel;
import com.unu.ms.MsConsejo.model.entity.ConsejoModel;
import com.unu.ms.MsConsejo.model.entity.MiembroConsejoModel;
import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;
import com.unu.ms.MsConsejo.repository.AsistenciaSesionRepository;
import com.unu.ms.MsConsejo.repository.ConsejoRepository;
import com.unu.ms.MsConsejo.repository.MiembroConsejoRepository;
import com.unu.ms.MsConsejo.repository.SesionConsejoRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ConsejoRepository consejoRepository;

    @Autowired
    private MiembroConsejoRepository miembroRepository;

    @Autowired
    private SesionConsejoRepository sesionRepository;

    @Autowired
    private AsistenciaSesionRepository asistenciaRepository;

    @Override
    public void run(String... args) {

        // OPCIÓN A: Ejecutar SOLO si la BD está vacía
        /* 
        if (consejoRepository.count() > 0) {
            System.out.println("La base ya tiene datos. No se cargarán registros iniciales.");
            return;
        }
        */

        // OPCIÓN B: LIMPIAR TODO Y CARGAR (Descomenta si quieres esta opción)
        
        asistenciaRepository.deleteAll();
        sesionRepository.deleteAll();
        miembroRepository.deleteAll();
        consejoRepository.deleteAll();
    
        cargarData();
    }

    private void cargarData() {

        // 1. CREAR CONSEJOS
        List<ConsejoModel> consejos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            ConsejoModel c = new ConsejoModel();
            c.setNombre("Consejo " + i);
            c.setDescripcion("Descripción del consejo " + i);
            c.setIdEstado(1);
            c.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            consejos.add(c);
        }
        consejoRepository.saveAll(consejos);

        // 2. CREAR MIEMBROS
        List<MiembroConsejoModel> miembros = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            MiembroConsejoModel m = new MiembroConsejoModel();
            m.setConsejo(consejos.get(i % 10)); // asigna consejo
            m.setIdPersona(i);
            m.setIdCargo(1);
            m.setFechaInicio(LocalDate.now().minusDays(30));
            m.setFechaFin(null);
            miembros.add(m);
        }
        miembroRepository.saveAll(miembros);

        // 3. CREAR SESIONES
        List<SesionConsejoModel> sesiones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            SesionConsejoModel s = new SesionConsejoModel();
            s.setConsejo(consejos.get(i % 10));
            s.setNumeroSesion("S-" + i);
            s.setFechaSesion(LocalDate.now());
            s.setIdTipoSesion(1);
            s.setDescripcion("Sesión número " + i);
            s.setIdEstado(1);
            s.setUsuarioRegistro(1);
            s.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
            sesiones.add(s);
        }
        sesionRepository.saveAll(sesiones);

        // 4. ASISTENCIAS
        List<AsistenciaSesionModel> asistencias = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AsistenciaSesionModel a = new AsistenciaSesionModel();
            a.setSesion(sesiones.get(i));
            a.setMiembro(miembros.get(i));
            a.setIdEstadoAsistencia(1);
            a.setUsuarioRegistro(1);
            a.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            asistencias.add(a);
        }
        asistenciaRepository.saveAll(asistencias);
    }
}
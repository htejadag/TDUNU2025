package com.unu.ms.MsConsejo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unu.ms.MsConsejo.model.entity.SesionConsejoModel;

@Repository
public interface SesionConsejoRepository extends JpaRepository<SesionConsejoModel, Integer> {

    public List<SesionConsejoModel> findByConsejo_IdConsejo(Integer idConsejo);
    public Optional<SesionConsejoModel> findByNumeroSesion(String numeroSesion);
    public List<SesionConsejoModel> findByFechaSesion(LocalDate fechaSesion);
    public List<SesionConsejoModel> findByIdTipoSesion(Integer idTipoSesion);
    public List<SesionConsejoModel> findByConsejo_IdConsejoAndFechaSesionBetween(Integer idConsejo, LocalDate fechaInicio, LocalDate fechaFin);
    
    // Contar sesiones por consejo
    long countByConsejo_IdConsejo(Integer idConsejo);
    
    @Query(

            value = "SELECT "
                    + "id_session AS idSession, "
                    + "id_consejo AS idConsejo, "
                    + "numero_sesion AS numeroSesion, "
                    + "fecha_sesion AS fechaSesion, "
                    + "id_tipo_sesion AS idTipoSesion, "
                    + "descripcion AS descripcion, "
                    + "id_estado AS idEstado "
                    + "FROM sesion_consejo "
                    + "WHERE id_consejo = :idConsejo", 
            nativeQuery = true

    )
    List<Object[]> listarSesiones(@Param("idConsejo") int idConsejo);

}

package Ms_Reingresante.Ms_Reingresante.model.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ficha_no_adeudo")
public class FichaNoAdeudoModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Ficha")
    private Integer idFicha; // id Ficha INT [cite: 106]

    @Column(name = "id_Proceso")
    private Integer idProceso; // id Proceso INT (Clave Foránea) [cite: 107]

    @Column(name = "Ficha_Numero")
    private String fichaNumero; // Ficha Numero VARCHAR(50) [cite: 108]

    @Column(name = "Ficha_Fecha_Emision")
    private LocalDate fechaEmision; // Ficha Fecha Emision DATE [cite: 109]

    @Column(name = "Ficha_Emitido_Por")
    private String emitidoPor; // Ficha Emitido Por VARCHAR(50) [cite: 110]

    // Campos de Auditoría
    @Column(name = "FECHA_CREACION")
    private LocalDate fechaCreacion; // FECHA CREACION DATETIME [cite: 111]

    @Column(name = "USUARIO_CREACION")
    private String usuarioCreacion; // USUARIO CREACION VARCHAR(50) [cite: 111]

    @Column(name = "FECHA_MODIFICACION")
    private LocalDate fechaModificacion; // FECHA MODIFICACION DATETIME [cite: 111]

    @Column(name = "USUARIO_MODIFICACION") // Se ajusta el length a 50 si es necesario
    private String usuarioModificacion; // USUARIO MODIFICACION VARCHAR(5) [cite: 111]
}
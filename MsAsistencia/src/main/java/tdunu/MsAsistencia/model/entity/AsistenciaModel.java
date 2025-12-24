package tdunu.MsAsistencia.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "asistencia")
public class AsistenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "sistema_origen", length = 50)
    private String sistemaOrigen;

    @Column(name = "tipo_entidad", length = 50)
    private String tipoEntidad;

    @Column(name = "entidad_id")
    private Integer entidadId;

    @Column(name = "tipo_evento", length = 50)
    private String tipoEvento;

    @Column(name = "evento_id")
    private Integer eventoId;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "observaciones", length = 500)
    private String observaciones;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "usuario_registro", length = 100)
    private String usuarioRegistro;
}

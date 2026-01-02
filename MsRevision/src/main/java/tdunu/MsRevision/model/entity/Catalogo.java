package tdunu.MsRevision.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "catalogo")
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_catalogo")
    private Integer idCatalogo;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "valor", length = 100)
    private String valor;

    @Column(name = "categoria", length = 50)
    private String categoria;

    @Column(name = "activo")
    private Boolean activo;
}
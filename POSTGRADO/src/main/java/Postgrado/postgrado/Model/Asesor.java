package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "asesor")
@Data
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAsesor;

    private String nombres;
    private String apellidos;
    private String gradoMaximo;
    private String cvRuta;
    private String declaracionRuta;

    private String estadoAsesor;
}

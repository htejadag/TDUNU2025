package Postgrado.postgrado.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "jurado")
@Data
public class Jurado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idJurado;

    private String nombres;
    private String apellidos;
    private String especialidad;
}

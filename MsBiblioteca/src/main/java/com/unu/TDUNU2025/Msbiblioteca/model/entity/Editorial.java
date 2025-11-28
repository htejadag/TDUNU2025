package com.unu.TDUNU2025.Msbiblioteca.model.entity;

import java.time.LocalDate;
@Entity
@Table(name = "editorial") // Define el nombre de la tabla en la BD
@Data // Genera Getters, Setters, toString, etc. (Lombok)
@NoArgsConstructor // Constructor vacío requerido por JPA
@AllArgsConstructor // Constructor con todos los argumentos
@Builder // Patrón Builder para crear objetos fácilmente
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    private Long idEditorial;

    @Column(nullable = false, length = 100)
    private String nombre;

    /**
     * NOTA: En la imagen "direccion" dice FK.
     * Si es una tabla externa, cambia 'String' por la Entidad 'Direccion'
     * y agrega la anotación @ManyToOne @JoinColumn(name="id_direccion").
     * Por ahora lo dejo como String para que compile.
     */
    @Column(length = 200)
    private String direccion;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Column(name = "sitio_web", length = 150)
    private String sitioWeb;

    @Column(name = "fecha_pago")
    private LocalDate fechaPago;

    @Column(length = 50)
    private String pais;
}

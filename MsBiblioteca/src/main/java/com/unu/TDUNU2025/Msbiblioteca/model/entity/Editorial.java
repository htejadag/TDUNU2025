<<<<<<< Updated upstream
    package com.unu.TDUNU2025.Msbiblioteca.model.entity;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import jakarta.persistence.Table;
    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;
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

        @Column(length = 200)
        private String direccion;

        @Column(length = 20)
        private String telefono;

        @Column(length = 100)
        private String email;

        @Column(name = "sitio_web", length = 150)
        private String sitioWeb;

        @Column(length = 50)
        private String pais;
    }
=======
package com.unu.TDUNU2025.Msbiblioteca.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "editorial")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class Editorial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_editorial")
    private Long idEditorial;

    @Column(name="nombre", length = 100, nullable = false, unique = true)
    private String nombre;

    @Column(name="direccion", length = 200)
    private String direccion;

    @Column(name="telefono", length = 20)
    private String telefono;

    @Column(name="email", length = 100)
    private String email;

    @Column(name = "sitio_web", length = 150)
    private String sitioWeb;

    @Column(name="pais", length = 50)
    private String pais;
    
    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    // Relación Inversa (Uno a Muchos) con Libro
    @OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY)
    @ToString.Exclude 
    @EqualsAndHashCode.Exclude 
    private Set<Libro> libros;
}
>>>>>>> Stashed changes

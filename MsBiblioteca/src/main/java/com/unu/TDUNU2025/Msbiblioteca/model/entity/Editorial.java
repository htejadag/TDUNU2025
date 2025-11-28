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

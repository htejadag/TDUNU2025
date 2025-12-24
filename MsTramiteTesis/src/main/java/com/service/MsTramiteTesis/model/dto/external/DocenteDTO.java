package com.service.MsTramiteTesis.model.dto.external;

/**
 * DTO para recibir información de docentes del MS Personas
 */
public class DocenteDTO {

    private Long id;
    private String nombres;
    private String apellidos;
    private String dni; // DNI único del docente/coordinador
    private String email;
    private String especialidad;
    private String grado;

    // Constructors
    public DocenteDTO() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}

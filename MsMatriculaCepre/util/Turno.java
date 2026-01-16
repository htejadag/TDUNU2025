package unu.MsMatriculaCepre.util;

public enum Turno {
    MAÑANA("Mañana"),
    TARDE("Tarde");
    
    private final String descripcion;
    
    Turno(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
}
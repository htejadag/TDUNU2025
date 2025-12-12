package ReIngreso.Mr_pago.model;


import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Pago")
public class pagoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Pago")
    private int id_Pago;

  @Column(name = "Pago_Metodo")
private String pagoMetodo;

//@Column(name = "Pago_Fecha")
//private LocalDate pagoFecha;

@Column(name = "Pago_Fecha", nullable = false)
private LocalDate pagoFecha = LocalDate.now();


@Column(name = "Pago_Monto")
private double pagoMonto;

@Column(name = "Pago_Numero_Boleta")
private String pagoNumeroBoleta;

@Column(name = "USUARIO_CREACION")
private String usuarioCreacion;

@Column(name = "FECHA_CREACION")
private LocalDate fechaCreacion = LocalDate.now();

@Column(name = "id_Proceso")
private int idProceso;

@Column(name = "id_Tipo_Pago")
private int idTipoPago;


    public int getId_Pago() {
        return id_Pago;
    }

    public void setId_Pago(int id_Pago) {
        this.id_Pago = id_Pago;
    }

    public int getId_Proceso() {
        return idProceso;
    }

    public void setId_Proceso(int id_Proceso) {
        this.idProceso = id_Proceso;
    }

    public int getId_Tipo_Pago() {
        return idTipoPago;
    }

    public void setId_Tipo_Pago(int id_Tipo_Pago) {
        this.idTipoPago = id_Tipo_Pago;
    }

    public LocalDate getPago_Fecha() {
        return pagoFecha;
    }

    public void setPago_Fecha(LocalDate Pago_Fecha) {
        this.pagoFecha = Pago_Fecha;
    }

    public double getPago_Monto() {
        return pagoMonto;
    }

    public void setPago_Monto(double Pago_Monto) {
        this.pagoMonto = Pago_Monto;
    }

    public String getPago_Numero_Boleta() {
        return pagoNumeroBoleta;
    }

    public void setPago_Numero_Boleta(String Pago_Numero_Boleta) {
        this.pagoNumeroBoleta = Pago_Numero_Boleta;
    }

    public String getPago_Metodo() {
        return pagoMetodo;
    }

    public void setPago_Metodo(String Pago_Metodo) {
        this.pagoMetodo = Pago_Metodo;
    }

    
    public String getUSUARIO_CREACION() {
        return usuarioCreacion;
    }

    public void setUSUARIO_CREACION(String USUARIO_CREACION) {
        this.usuarioCreacion = USUARIO_CREACION;
    }
    
    public LocalDate getFecha_creacion() {
    return fechaCreacion;
}

public void setFecha_creacion(LocalDate fecha_creacion) {
    this.fechaCreacion = fecha_creacion;
}

}






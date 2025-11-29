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

    @Column(name = "id_Proceso")
    private int id_Proceso;

    @Column(name = "id_Tipo_Pago")
    private int id_Tipo_Pago;

    @Column(name = "Pago_Fecha")
    private LocalDate Pago_Fecha;

    @Column(name = "Pago_Monto")
    private double Pago_Monto;

    @Column(name = "Pago_Numero_Noleta")
    private String Pago_Numero_Boleta;

    @Column(name = "Pago_Metodo")
    private String Pago_Metodo;

    @Column(name = "FECHA_CREACION")
    private LocalDate FECHA_CREACION;

    @Column(name = "USUARIO_CREACION")
    private String USUARIO_CREACION;

    public int getId_Pago() {
        return id_Pago;
    }

    public void setId_Pago(int id_Pago) {
        this.id_Pago = id_Pago;
    }

    public int getId_Proceso() {
        return id_Proceso;
    }

    public void setId_Proceso(int id_Proceso) {
        this.id_Proceso = id_Proceso;
    }

    public int getId_Tipo_Pago() {
        return id_Tipo_Pago;
    }

    public void setId_Tipo_Pago(int id_Tipo_Pago) {
        this.id_Tipo_Pago = id_Tipo_Pago;
    }

    public LocalDate getPago_Fecha() {
        return Pago_Fecha;
    }

    public void setPago_Fecha(LocalDate Pago_Fecha) {
        this.Pago_Fecha = Pago_Fecha;
    }

    public double getPago_Monto() {
        return Pago_Monto;
    }

    public void setPago_Monto(double Pago_Monto) {
        this.Pago_Monto = Pago_Monto;
    }

    public String getPago_Numero_Boleta() {
        return Pago_Numero_Boleta;
    }

    public void setPago_Numero_Boleta(String Pago_Numero_Boleta) {
        this.Pago_Numero_Boleta = Pago_Numero_Boleta;
    }

    public String getPago_Metodo() {
        return Pago_Metodo;
    }

    public void setPago_Metodo(String Pago_Metodo) {
        this.Pago_Metodo = Pago_Metodo;
    }

    public LocalDate getFECHA_CREACION() {
        return FECHA_CREACION;
    }

    public void setFECHA_CREACION(LocalDate FECHA_CREACION) {
        this.FECHA_CREACION = FECHA_CREACION;
    }

    public String getUSUARIO_CREACION() {
        return USUARIO_CREACION;
    }

    public void setUSUARIO_CREACION(String USUARIO_CREACION) {
        this.USUARIO_CREACION = USUARIO_CREACION;
    }
}






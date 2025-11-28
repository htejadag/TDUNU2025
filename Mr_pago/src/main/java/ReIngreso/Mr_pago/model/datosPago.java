package ReIngreso.Mr_pago.model;

import java.io.Serializable;
import java.time.LocalDate;

public class datosPago implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id_Pago ;
    private int id_Proceso  ;
    private int id_Tipo_Pago;
    private LocalDate Pago_Fecha;
    private double Pago_Monto;
    private String Pago_Numero_Boleta;
    private String Pago_Metodo;
    private LocalDate FECHA_CREACION;
    private String USUARIO_CREACION;
   
public datosPago(){

    }

 public datosPago
 
 (
            int id_Pago,
            int id_Proceso,
            int id_Tipo_Pago,
            LocalDate Pago_Fecha,
            double Pago_Monto,
            String Pago_Numero_Boleta,
            String Pago_Metodo,
            LocalDate FECHA_CREACION,
            String USUARIO_CREACION
    ) {
        this.setId_Pago(id_Pago);
        this.setId_Proceso(id_Proceso);
        this.setId_Tipo_Pago(id_Tipo_Pago);
        this.setPago_Fecha(Pago_Fecha);
        this.setPago_Monto(Pago_Monto);
        this.setPago_Numero_Boleta(Pago_Numero_Boleta);
        this.setPago_Metodo(Pago_Metodo);
        this.setFECHA_CREACION(FECHA_CREACION);
        this.setUSUARIO_CREACION(USUARIO_CREACION);
    }

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

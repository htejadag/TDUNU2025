package com.pago.util;

public class ApiRoutes {

  public static class Demo {

    public static final String BASE = "/api/demo";

    // GENERALES PARA TODOS LOS CONTROLADORES
    public static final String LISTAR = "/listar";
    public static final String OBTENER_POR_ID = "/obtenerPorId";
    public static final String GUARDAR = "/guardar";
    public static final String EDITAR = "/editar";
    public static final String ELIMINAR = "/eliminar";

    // APERTURA CIERRE CAJA - NO TOCAR
    public static final String APE_CIE_CAJA = "/AperturaCierreCaja";
    // CONCEPTO PAGO - NO TOCAR
    public static final String CONCEPTO_PAGO = "/ConceptoPago";
    // CLASIFICADOR INGRESO - NO TOCAR
    public static final String CLA_INGRESO = "/ClasificadorIngreso";

  }

}

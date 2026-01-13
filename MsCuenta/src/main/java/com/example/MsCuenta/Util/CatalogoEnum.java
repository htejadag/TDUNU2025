package com.example.MsCuenta.Util;

public enum CatalogoEnum {

    RECARGA(1),
    CONSUMO(2),
    EFECTIVO(3),
    TARJETA(4),
    YAPE(5),
    PLIN(6);

    private final int id;

    CatalogoEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}

package com.example.Comedor.util;

public enum CatalogoEnum {


    MANANA(1),
    TARDE(2),
    NOCHE(3),
    DESAYUNO(4),
    ALMUERZO(5),
    CENA(6);

    private final int id;

    CatalogoEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    
    
}

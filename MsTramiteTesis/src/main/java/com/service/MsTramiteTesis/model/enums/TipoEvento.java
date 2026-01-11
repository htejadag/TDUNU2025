package com.service.MsTramiteTesis.model.enums;

public enum TipoEvento {
    // Eventos de Proyecto
    PROYECTO_CREADO,
    PROYECTO_MODIFICADO,
    PROYECTO_APROBADO_COORDINADOR,
    PROYECTO_RECHAZADO_COORDINADOR,

    // Eventos de Asesor
    ASESOR_ASIGNADO,
    ASESOR_MODIFICADO,

    // Eventos de Revisión
    REVISION_ASESOR_APROBADA,
    REVISION_ASESOR_OBSERVADA,
    REVISION_JURADO_APROBADA,
    REVISION_JURADO_OBSERVADA,

    // Eventos de Borrador
    BORRADOR_SUBIDO,
    BORRADOR_APROBADO,
    BORRADOR_RECHAZADO,

    // Eventos de Acta
    ACTA_GENERADA,

    // Eventos Generales
    NOTIFICACION_GENERAL
}

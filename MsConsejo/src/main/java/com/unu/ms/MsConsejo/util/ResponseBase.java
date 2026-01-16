package com.unu.ms.MsConsejo.util;

import lombok.Builder;
import lombok.Data;

/**
 * Clase genérica que encapsula la respuesta de todos los endpoints de la API.
 * 
 * Proporciona una estructura uniforme para las respuestas REST, incluyendo indicador
 * de éxito, mensaje descriptivo y datos de respuesta. Este formato facilita el
 * consumo de la API desde clientes y proporciona mejor experiencia para el manejo
 * de errores y éxito.
 * 
 * Parámetro de tipo T: tipo de datos que contiene la respuesta
 * 
 * @param <T> el tipo de datos contenido en la respuesta
 * 
 * @author Microservicio de Consejo
 * @version 1.0
 * @since 2024
 */
@Data
@Builder
public class ResponseBase<T> {
  
  /** Indicador booleano que señala si la operación fue exitosa */
  private boolean success;
  
  /** Mensaje descriptivo sobre el resultado de la operación */
  private String message;
  
  /** Datos de la respuesta (puede ser nulo en caso de error o eliminación) */
  private T data;

  /**
   * Crea una respuesta exitosa con solo los datos.
   * 
   * @param data los datos a incluir en la respuesta
   * @return ResponseBase con success=true y mensaje genérico
   * 
   * @param <T> el tipo de datos de la respuesta
   */
  public static <T> ResponseBase<T> ok(T data) {
    return ResponseBase.<T>builder()
        .success(true)
        .message("Operación exitosa")
        .data(data)
        .build();
  }

  /**
   * Crea una respuesta exitosa con mensaje personalizado y datos.
   * 
   * @param message mensaje personalizado para la respuesta
   * @param data los datos a incluir en la respuesta
   * @return ResponseBase con success=true y mensaje personalizado
   * 
   * @param <T> el tipo de datos de la respuesta
   */
  public static <T> ResponseBase<T> ok(String message, T data) {
    return ResponseBase.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
  }

  /**
   * Crea una respuesta de error.
   * 
   * @param message mensaje de error a incluir en la respuesta
   * @return ResponseBase con success=false y sin datos
   * 
   * @param <T> el tipo de datos de la respuesta
   */
  public static <T> ResponseBase<T> error(String message) {
    return ResponseBase.<T>builder()
        .success(false)
        .message(message)
        .build();
  }

}

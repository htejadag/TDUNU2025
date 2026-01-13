package Ms_Reingresante.Ms_Reingresante.util;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ResolucionBase<T> {
  private boolean success;
  private String message;
  private T data;

  public static <T> ResolucionBase<T> ok(T data) {
    return ResolucionBase.<T>builder()
        .success(true)
        .message("Resolucion, operación exitosa")
        .data(data)
        .build();
  }

  public static <T> ResolucionBase<T> ok(String message, T data) {
    return ResolucionBase.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
  }

  public static <T> ResolucionBase<T> error(String message) {
    return ResolucionBase.<T>builder()
        .success(false)
        .message(message)
        .build();
  }



}

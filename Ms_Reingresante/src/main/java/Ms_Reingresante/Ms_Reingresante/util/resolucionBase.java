package Ms_Reingresante.Ms_Reingresante.util;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class resolucionBase<T> {
    private boolean success;
  private String message;
  private T data;

  public static <T> resolucionBase<T> ok(T data) {
    return resolucionBase.<T>builder()
        .success(true)
        .message("Resolucion, operación exitosa")
        .data(data)
        .build();
  }

  public static <T> resolucionBase<T> ok(String message, T data) {
    return resolucionBase.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
  }

  public static <T> resolucionBase<T> error(String message) {
    return resolucionBase.<T>builder()
        .success(false)
        .message(message)
        .build();
  }



}

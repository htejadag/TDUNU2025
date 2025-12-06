package Ms_Reingresante.Ms_Reingresante.util;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class procesoReingresanteBase<T> {
    private boolean success;
  private String message;
  private T data;

  public static <T> procesoReingresanteBase<T> ok(T data) {
    return procesoReingresanteBase.<T>builder()
        .success(true)
        .message("Proceso Reingresante, operación exitosa")
        .data(data)
        .build();
  }

  public static <T> procesoReingresanteBase<T> ok(String message, T data) {
    return procesoReingresanteBase.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
  }

  public static <T> procesoReingresanteBase<T> error(String message) {
    return procesoReingresanteBase.<T>builder()
        .success(false)
        .message(message)
        .build();
  }



}

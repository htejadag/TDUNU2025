package Ms_Reingresante.Ms_Reingresante.util;


import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ProcesoReingresanteBase<T> {
    private boolean success;
  private String message;
  private T data;

  public static <T> ProcesoReingresanteBase<T> ok(T data) {
    return ProcesoReingresanteBase.<T>builder()
        .success(true)
        .message("Proceso Reingresante, operación exitosa")
        .data(data)
        .build();
  }

  public static <T> ProcesoReingresanteBase<T> ok(String message, T data) {
    return ProcesoReingresanteBase.<T>builder()
        .success(true)
        .message(message)
        .data(data)
        .build();
  }

  public static <T> ProcesoReingresanteBase<T> error(String message) {
    return ProcesoReingresanteBase.<T>builder()
        .success(false)
        .message(message)
        .build();
  }



}

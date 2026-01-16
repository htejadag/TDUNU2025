package tdunu.MsCatalogo.util;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBase<T> {
    private boolean success;
    private String message;
    private T data;
    private String source; // "CACHE" o "DATABASE"

    public static <T> ResponseBase<T> ok(T data) {
        return ResponseBase.<T>builder()
                .success(true)
                .message("Operación exitosa")
                .data(data)
                .build();
    }

    public static <T> ResponseBase<T> ok(String message, T data) {
        return ResponseBase.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ResponseBase<T> okFromCache(T data) {
        return ResponseBase.<T>builder()
                .success(true)
                .message("Operación exitosa")
                .data(data)
                .source("CACHE")
                .build();
    }

    public static <T> ResponseBase<T> okFromDatabase(T data) {
        return ResponseBase.<T>builder()
                .success(true)
                .message("Operación exitosa")
                .data(data)
                .source("DATABASE")
                .build();
    }

    public static <T> ResponseBase<T> error(String message) {
        return ResponseBase.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}

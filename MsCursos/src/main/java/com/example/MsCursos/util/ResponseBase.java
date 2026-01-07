package com.example.mscursos.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBase<T> {

    private boolean success;
    private String message;
    private T data;

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

    public static <T> ResponseBase<T> error(String message) {
        return ResponseBase.<T>builder()
                .success(false)
                .message(message)
                .build();
    }
}

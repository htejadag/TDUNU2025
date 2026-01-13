package com.unu.MsDocumentos.utils;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBase<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> ResponseBase<T> ok(T data) {
        return ResponseBase.<T>builder()
                .success(true)
                .message(Mensajes.OPERACION_EXITOSA)
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

    public static <T> ResponseBase<T> notFound(String message) {
        return ResponseBase.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

}

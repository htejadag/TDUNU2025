package com.ms_simulacro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private Metadata metadata;
    private T data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Metadata {
        private String estatus;
        private String message;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .metadata(Metadata.builder()
                        .estatus("OK")
                        .message(message)
                        .build())
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .metadata(Metadata.builder()
                        .estatus("ERROR")
                        .message(message)
                        .build())
                .build();
    }
}

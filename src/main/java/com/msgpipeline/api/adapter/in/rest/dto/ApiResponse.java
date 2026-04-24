package com.msgpipeline.api.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

// Envelope genérico para todas las respuestas de la API.
// T: tipo del dato principal de la respuesta (MessageResponse, List<...>, etc.)
@Data
@Builder
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String timestamp;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true).message(message).data(data)
                .timestamp(Instant.now().toString()).build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .success(false).message(message)
                .timestamp(Instant.now().toString()).build();
    }
}

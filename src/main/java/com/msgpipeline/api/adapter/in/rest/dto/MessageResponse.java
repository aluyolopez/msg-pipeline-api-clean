package com.msgpipeline.api.adapter.in.rest.dto;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;

// DTO de respuesta — expone solo los campos necesarios al cliente.
// Nunca exponer la entidad de dominio directamente (evita acoplamiento).
@Data
@Builder
public class MessageResponse {
    private String messageId;
    private String content;
    private String type;
    private String status;
    private Instant createdAt;
    private Instant processedAt;
}

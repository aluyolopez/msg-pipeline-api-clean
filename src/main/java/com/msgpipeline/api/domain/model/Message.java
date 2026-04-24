package com.msgpipeline.api.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

// Entidad de dominio PURA — sin anotaciones de framework ni de persistencia.
// Principio: el dominio no sabe que existe DynamoDB, Spring ni AWS.
@Data
@Builder
public class Message {

    private String messageId;      // UUID generado al crear
    private String content;        // contenido del mensaje
    private String type;           // NOTIFICATION | RECORD | AUDIT
    private String recipientEmail; // destinatario de la notificación
    private Integer priority;      // 1=baja, 5=alta
    private String status;         // PENDING | PROCESSING | COMPLETED | FAILED
    private Instant createdAt;     // timestamp de creación
    private Instant processedAt;   // timestamp de procesamiento (nullable)

    // Factory method: encapsula la lógica de creación de un nuevo mensaje.
    // Evita que el controller conozca la lógica de inicialización.
    public static Message create(String content, String type,
                                  String recipientEmail, Integer priority) {
        return Message.builder()
                .messageId(UUID.randomUUID().toString())
                .content(content).type(type)
                .recipientEmail(recipientEmail)
                .priority(priority != null ? priority : 3)
                .status("PENDING")
                .createdAt(Instant.now())
                .build();
    }
}

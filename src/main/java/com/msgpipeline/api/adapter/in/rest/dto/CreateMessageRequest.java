package com.msgpipeline.api.adapter.in.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

// @Data: genera getters, setters, equals, hashCode y toString (Lombok).
// @Builder: patrón Builder para construcción fluida del objeto.
@Data
@Builder
public class CreateMessageRequest {

    // @NotBlank: el campo no puede ser nulo, vacío ni solo espacios en blanco.
    // message: mensaje de error en español para retroalimentación al cliente.
    @NotBlank(message = "El contenido del mensaje es obligatorio")
    @Size(min = 1, max = 1000, message = "El mensaje debe tener entre 1 y 1000 caracteres")
    private String content;

    // @NotBlank combinado con @Pattern: valida formato específico.
    @NotBlank(message = "El tipo de mensaje es obligatorio")
    @Pattern(regexp = "^(NOTIFICATION|RECORD|AUDIT)$",
             message = "El tipo debe ser NOTIFICATION, RECORD o AUDIT")
    private String type;

    // @Email: valida formato de correo electrónico.
    @Email(message = "El correo del destinatario no tiene formato válido")
    private String recipientEmail;

    // @Min/@Max: rango numérico para prioridad del mensaje.
    @Min(value = 1, message = "La prioridad mínima es 1")
    @Max(value = 5, message = "La prioridad máxima es 5")
    private Integer priority;
}
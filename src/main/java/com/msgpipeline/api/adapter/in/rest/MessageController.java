package com.msgpipeline.api.adapter.in.rest;

import com.msgpipeline.api.adapter.in.rest.dto.CreateMessageRequest;
import com.msgpipeline.api.adapter.in.rest.dto.MessageResponse;
import com.msgpipeline.api.adapter.in.rest.dto.ApiResponse;
import com.msgpipeline.api.domain.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// @RestController: combina @Controller + @ResponseBody — respuestas automáticas en JSON.
// @RequestMapping: prefijo de URL compartido por todos los métodos del controller.
// @Tag: etiqueta para organizar endpoints en la documentación OpenAPI/Swagger.
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Tag(name = "Mensajes", description = "API de gestión de mensajes del pipeline")
public class MessageController {

    // Inyección por constructor (patrón recomendado sobre @Autowired).
    // MessageService es la interfaz del puerto de entrada — seguimos Dependency Inversion.
    private final MessageService messageService;

    // @Operation: documentación del endpoint en Swagger.
    // @PostMapping: maneja solicitudes HTTP POST.
    // @Valid: activa las validaciones de Bean Validation en el DTO de entrada.
    @Operation(summary = "Crear mensaje", description = "Envía un nuevo mensaje al pipeline de procesamiento")
    @PostMapping
    public ResponseEntity<ApiResponse<MessageResponse>> createMessage(
            @Valid @RequestBody CreateMessageRequest request) {
        MessageResponse response = messageService.createMessage(request);
        // 201 Created: código correcto para la creación exitosa de un recurso.
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "Mensaje creado exitosamente"));
    }

    @Operation(summary = "Obtener mensaje por ID")
    @GetMapping("/{messageId}")
    public ResponseEntity<ApiResponse<MessageResponse>> getMessage(
            @PathVariable String messageId) {
        MessageResponse response = messageService.getMessage(messageId);
        return ResponseEntity.ok(ApiResponse.success(response, "Mensaje encontrado"));
    }
}
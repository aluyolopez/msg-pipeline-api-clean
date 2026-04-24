// Servicio de dominio — implementa los casos de uso.
package com.msgpipeline.api.domain.service;

import com.msgpipeline.api.adapter.in.rest.dto.CreateMessageRequest;
import com.msgpipeline.api.adapter.in.rest.dto.MessageResponse;
import com.msgpipeline.api.domain.model.Message;
import com.msgpipeline.api.domain.port.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageResponse createMessage(CreateMessageRequest request) {
        log.info("Creando mensaje de tipo: {}", request.getType());
        Message message = Message.create(
            request.getContent(), request.getType(),
            request.getRecipientEmail(), request.getPriority()
        );
        Message saved = messageRepository.save(message);
        log.info("Mensaje creado con ID: {}", saved.getMessageId());
        return toResponse(saved);
    }

    public MessageResponse getMessage(String messageId) {
        return messageRepository.findById(messageId)
            .map(this::toResponse)
            .orElseThrow(() -> new IllegalArgumentException(
                "Mensaje no encontrado con ID: " + messageId));
    }

    private MessageResponse toResponse(Message m) {
        return MessageResponse.builder()
            .messageId(m.getMessageId()).content(m.getContent())
            .type(m.getType()).status(m.getStatus())
            .createdAt(m.getCreatedAt()).processedAt(m.getProcessedAt())
            .build();
    }
}
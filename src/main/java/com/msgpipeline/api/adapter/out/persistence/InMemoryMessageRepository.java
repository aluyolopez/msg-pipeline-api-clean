package com.msgpipeline.api.adapter.out.persistence;

import com.msgpipeline.api.domain.model.Message;
import com.msgpipeline.api.domain.port.MessageRepository;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

// Implementación en memoria del puerto de salida — solo para desarrollo local.
// En Sesión 03 reemplazaremos esta clase con DynamoMessageRepository.
// El controller y el servicio NO cambian — gracias a Dependency Inversion.
@Repository
public class InMemoryMessageRepository implements MessageRepository {

    // ConcurrentHashMap: thread-safe para entornos multi-hilo.
    private final Map<String, Message> store = new ConcurrentHashMap<>();

    @Override
    public Message save(Message message) {
        store.put(message.getMessageId(), message);
        return message;
    }

    @Override
    public Optional<Message> findById(String messageId) {
        return Optional.ofNullable(store.get(messageId));
    }
}

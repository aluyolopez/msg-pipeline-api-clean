// Puerto de salida — interfaz que el adaptador de persistencia debe implementar.
// El dominio depende de esta abstracción, no de DynamoDB (Dependency Inversion).
package com.msgpipeline.api.domain.port;
import com.msgpipeline.api.domain.model.Message;
import java.util.Optional;

public interface MessageRepository {
    Message save(Message message);
    Optional<Message> findById(String messageId);
}
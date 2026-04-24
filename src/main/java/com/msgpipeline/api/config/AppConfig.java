package com.msgpipeline.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// @ConfigurationProperties: mapea el prefijo "app" del application.yml a esta clase.
// Ventaja: tipado fuerte — las propiedades se inyectan automáticamente.
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfig {

    // Clase interna para agrupar propiedades de AWS
    private Aws aws = new Aws();

    @Data
    public static class Aws {
        // Región AWS — requerida en todos los perfiles
        private String region = "us-east-1";

        // Nombre de la tabla DynamoDB — se configura en application-local.yml y Lambda
        private String dynamodbTable;

        // URL de la cola SQS — se usará desde la Sesión 03
        // Opcional en Sesión 01 (no hay SQS aún)
        private String sqsQueueUrl;
    }
}

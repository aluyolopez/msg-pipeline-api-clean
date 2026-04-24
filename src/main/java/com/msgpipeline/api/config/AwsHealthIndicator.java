package com.msgpipeline.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

// HealthIndicator: Spring Boot Actuator invoca el método health() periódicamente.
// Visible en /actuator/health con los detalles configurados en application.yml.
@Component("awsConnectivity")
@RequiredArgsConstructor
public class AwsHealthIndicator implements HealthIndicator {

    private final AppConfig appConfig;

    @Override
    public Health health() {
        AppConfig.Aws aws = appConfig.getAws();

        // Sesión 01: verificar que la tabla DynamoDB está configurada
        if (aws.getDynamodbTable() == null || aws.getDynamodbTable().isBlank()) {
            return Health.down()
                .withDetail("error", "DYNAMODB_TABLE_NAME no configurado")
                .build();
        }

        // Sesión 01: SQS es opcional — se configura desde Sesión 03
        Health.Builder builder = Health.up()
            .withDetail("region", aws.getRegion())
            .withDetail("dynamodb-table", aws.getDynamodbTable());

        if (aws.getSqsQueueUrl() != null && !aws.getSqsQueueUrl().isBlank()) {
            builder.withDetail("sqs-queue", aws.getSqsQueueUrl());
        } else {
            builder.withDetail("sqs-queue", "no configurado (se configura en Sesión 03)");
        }

        // En sesiones posteriores: verificar conectividad real con AWS SDK
        return builder.build();
    }
}

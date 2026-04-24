package com.msgpipeline.api;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

// @SpringBootApplication: activa auto-configuración completa de Spring Boot.
// Habilita: @ComponentScan, @EnableAutoConfiguration, @Configuration.
@SpringBootApplication
public class MsgPipelineApiApplication {

    public static void main(String[] args) {
        // SpringApplicationBuilder: fuerza el tipo SERVLET explícitamente.
        // Necesario para compatibilidad con aws-serverless-java-container 2.0.3
        // y Spring Boot 3.5 — garantiza arranque correcto tanto en local como en Lambda.
        new SpringApplicationBuilder(MsgPipelineApiApplication.class)
            .web(WebApplicationType.SERVLET)
            .run(args);
    }
}

package com.msgpipeline.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    // @Bean: declara este método como productor de un bean gestionado por Spring.
    @Bean
    public OpenAPI msgPipelineOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("msg-pipeline API")
                .description("API REST del pipeline de mensajería serverless en AWS")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Especialista Spring Boot & AWS Serverless")
                    .email("soporte@msgpipeline.com")))
            .servers(List.of(
                new Server().url("http://localhost:8080").description("Local"),
                new Server().url("https://api.msgpipeline.com").description("Producción")
            ));
    }
}
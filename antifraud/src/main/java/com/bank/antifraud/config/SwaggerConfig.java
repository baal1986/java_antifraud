package com.bank.antifraud.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Baal"), description = "API Anti-Fraud"))
@Configuration
public class SwaggerConfig {
//http://localhost:8086/api/anti-fraud/swagger-ui/index.html#/
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API")
                        .description("Anti-Fraud")
                );
    }
}


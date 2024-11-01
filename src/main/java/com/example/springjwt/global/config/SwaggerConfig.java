package com.example.springjwt.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Board API",
                version = "1.0"
        )
)
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )
                .components(
                        new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
                )
                .addServersItem(new Server().url("/"))
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Board API")
                                .description("Board API Spec")
                                .version("v1.0.0")
                );
    }

    public SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
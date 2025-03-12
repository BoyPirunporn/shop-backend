package com.backend.shop.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth").addList("x-api-key");

    License license = new License().name("Apache 2.0").url("http://springdoc.org");

    Info info = new Info().title("Ecommerce manager").description("This is ecommerce service").version("v0.0.1")
            .license(license);
    SecurityScheme securityScheme = new SecurityScheme().name("bearerAuth")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer").bearerFormat("JWT");
    // SecurityScheme securityApiKey = new SecurityScheme().name("x-api-key")
    //         .type(SecurityScheme.Type.APIKEY)
    //         .in(SecurityScheme.In.HEADER)
    //         .description("Custom Schema Header");

    Components components = new Components().addSecuritySchemes("bearerAuth", securityScheme);
            // .addSecuritySchemes("x-api-key", securityApiKey);

    ExternalDocumentation externalDocumentation = new ExternalDocumentation()
            .description("SpringBoot Wigi Documentation").url("https://springboot.wiki.github.org/docs");

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(info)
                .externalDocs(externalDocumentation)
                .addSecurityItem(securityRequirement)
                .components(components);
    }

}

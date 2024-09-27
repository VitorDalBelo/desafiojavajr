package com.vitor.crudjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("Crud java")
                .version("V1")
                .description("teste")
                .license(
                    new License()
                        .name("Apache 2.0")
                        .url("https://localhost")
                )
            );
    }
}

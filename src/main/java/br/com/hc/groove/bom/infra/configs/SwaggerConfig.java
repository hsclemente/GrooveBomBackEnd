package br.com.hc.groove.bom.infra.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    
    @Bean
     public OpenAPI customOpenAPI() {
       return new OpenAPI()
                    .components(new Components()
                        .addSecuritySchemes("bearer-key", 
                            new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                        .bearerFormat("JWT")))
                        .info(new Info()
                                    .title("API Groove Bom")
                                    .description("API responsável pelo BackEnd do site Groove Bom")
                                    .contact(new Contact()
                                                    .name("Henrique Silva Clemente")
                                                    .email("hclemenente@furb.br")));
    }
}
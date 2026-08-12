package main.java.backend.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI airportPlaygroundOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Airport Operations Playground API")
                        .description("REST API developed to practice modern Java, Spring Boot and Angular while simulating airport operations.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Khadija Moussafi")
                                .email("doheny5000@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}
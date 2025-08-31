package com.nathan.blogmanagementapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Create contact information
        Contact contact = new Contact()
                .email("boutchouangelija@gmail.com")
                .name("Boutchouang Nathan Elija");

        // Create license information
        License mitLicense = new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        // Define server
        Server localServer = new Server()
                .url("http://localhost:8080")
                .description("Local Development Server");

        // Create and return a customized OpenAPI instance
        return new OpenAPI()
                .info(new Info()
                        .title("Blog Management API")
                        .version("1.0")
                        .contact(contact)
                        .description("API for managing a blog with articles and comments. This API allows users to create, read, update, and delete blog articles and add comments to articles.")
                        .license(mitLicense))
                .components(new Components())
                .servers(Arrays.asList(localServer));
    }
}

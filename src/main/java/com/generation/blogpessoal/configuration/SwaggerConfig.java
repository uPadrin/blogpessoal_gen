package com.generation.blogpessoal.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI springBlogPessoalOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Projeto Blog Pessoal")
                        .description("Projeto Blog Pessoal - Generation Brasil")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Genaration Brasil")
                                .url("https://brazil.generation.org/"))
                        .contact(new Contact()
                                .name("uPadrin")
                                .url("https://github.com/uPadrin/blogpessoal_gen")
                                .email("bryan.vieira2013@gmail.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/uPadrin/blogpessoal_gen"));

    }

    @Bean
    OpenApiCustomizer customerGlobalHeaderOpenApiCustomiser() {
        return openApi -> {
            openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations()
                    .forEach(operation -> {

                        ApiResponses apiResponse = operation.getResponses();

                        apiResponse.addApiResponse("200", createApiResponse("Sucesso!"));
                        apiResponse.addApiResponse("201", createApiResponse("Objeto Persistido!"));
                        apiResponse.addApiResponse("204", createApiResponse("Objeto Excluído!"));
                        apiResponse.addApiResponse("400", createApiResponse("Erro na Requisição"));
                        apiResponse.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
                        apiResponse.addApiResponse("403", createApiResponse("Acesso Proibido"));
                        apiResponse.addApiResponse("404", createApiResponse("Objeto Não Encontrado"));
                        apiResponse.addApiResponse("500", createApiResponse("Erro na Aplicação"));
                    }));
        };
    }

    private ApiResponse createApiResponse(String message) {
        return new ApiResponse().description(message);
    }
}

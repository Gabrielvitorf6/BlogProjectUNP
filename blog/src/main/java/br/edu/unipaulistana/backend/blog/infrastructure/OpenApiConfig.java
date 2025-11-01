package br.edu.unipaulistana.backend.blog.infrastructure;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info().title("Spring Boot UNP BackEnd")
                        .version("1.0")
                            .description("Spring Boot UNP docs"));
    }

    }


package hello.numberone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Number One API")
                .description("Student/Movie CRUD API documentation")
                .version("v1")
                .contact(new Contact().name("number-one").email("dev@example.com"))
                .license(new License().name("Apache 2.0")));
    }
}


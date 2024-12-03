package uz.pdp.WebAuto.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApi {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("WebAuto")
                        .description("Car Dealer Server")
                        .contact(new Contact()
                                .email("odiljonbaxriddinov735@gmail.com"))
                        .version("1.0.00"))
                .servers(List.of(
                        /*new Server().url("http://13.60.215.5:8080")
                                .description("For Product Application"),*/
                        new Server().url("http://localhost:8080")
                                .description("For Product Application")))

                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearer-jwt"));

    }

    @Bean
    public GroupedOpenApi all() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**").build();
    }

    @Bean
    public GroupedOpenApi adminUser() {
        return GroupedOpenApi.builder()
                .group("admin-user")
                .pathsToMatch("/admin/user/**").build();
    }

    @Bean
    public GroupedOpenApi products() {
        return GroupedOpenApi.builder()
                .group("products")
                .pathsToMatch("/admin/product/**").build();
    }

    @Bean
    public GroupedOpenApi category() {
        return GroupedOpenApi.builder()
                .group("categories")
                .pathsToMatch("/admin/category/**").build();
    }

    @Bean
    public GroupedOpenApi home() {
        return GroupedOpenApi.builder()
                .group("home")
                .pathsToMatch("/user/**").build();
    }

    @Bean
    public GroupedOpenApi auth() {
        return GroupedOpenApi.builder()
                .group("authentication")
                .pathsToMatch("/auth/**").build();
    }

    @Bean
    public GroupedOpenApi categories() {
        return GroupedOpenApi.builder()
                .group("orders")
                .pathsToMatch("/category/**").build();
    }

}

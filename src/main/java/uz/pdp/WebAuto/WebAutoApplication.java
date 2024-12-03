package uz.pdp.WebAuto;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class WebAutoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAutoApplication.class, args);
	}

	@Bean
	public AuditorAware<Long> auditorAware() {
		return () -> Optional.ofNullable(1L);
	}

	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("WebAuto")
						.description("This document is designed to explain the project")
						.version("1.0.0")
						.contact(new Contact()
								.name(" Developer group")
								.url("https://github.com/Abduraxmonov"))
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org"))
						.termsOfService("http://swagger.io/terms/"))
				.externalDocs(new ExternalDocumentation()
						.description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"))
				.servers(List.of(
						new Server().url("http://localhost:8080").description("Test Server")
				))
				.addSecurityItem(new SecurityRequirement()
						.addList("bearerAuth"))
				.components(new Components()
						.addSecuritySchemes("bearerAuth", new SecurityScheme()
								.name("bearerAuth")
								.type(SecurityScheme.Type.HTTP)
								.scheme("bearer")
								.bearerFormat("JWT")));
	}

}

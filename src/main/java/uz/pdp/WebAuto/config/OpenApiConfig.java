package uz.pdp.WebAuto.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Car Dealer Management System",
                description = "This API provides functionalities to manage cars, dealers, and brands. "
                        + "Users can browse available cars, filter them by specific criteria, "
                        + "and manage dealers and their inventory effectively.",
                contact = @Contact(
                        name = "Car Dealer Support Team"
                ),
                version = "v1.0.0"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server"),
                @Server(url = "https://api.cardealer.com", description = "Production Server")
        }
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        description = "Provide your JWT token to access secured endpoints. "
                + "Use the `/login` endpoint to get a valid token.",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}

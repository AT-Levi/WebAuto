package uz.pdp.WebAuto.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import uz.pdp.WebAuto.dtos.AppErrorDTO;

import java.io.IOException;

@Configuration
public class CustomHandler {

    private final ObjectMapper objectMapper;

    public CustomHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            AppErrorDTO appErrorDto = new AppErrorDTO(
                    accessDeniedException.getMessage(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    403
            );
            writeErrorResponse(response, 403, appErrorDto);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            AppErrorDTO appErrorDto = new AppErrorDTO(
                    authException.getMessage(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    401
            );
            writeErrorResponse(response, 401, appErrorDto);
        };
    }

    private void writeErrorResponse(jakarta.servlet.http.HttpServletResponse response, int statusCode, AppErrorDTO errorDto) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            objectMapper.writeValue(outputStream, errorDto);
            outputStream.flush();
        }
    }
}
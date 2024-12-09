package uz.pdp.WebAuto.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import uz.pdp.WebAuto.dtos.error.ErrorResDTO;

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
            ErrorResDTO errorResDto = new ErrorResDTO(
                    accessDeniedException.getMessage(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    403
            );
            writeErrorResponse(response, 403, errorResDto);
        };
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            ErrorResDTO errorResDto = new ErrorResDTO(
                    authException.getMessage(),
                    request.getRequestURI(),
                    request.getRequestURL().toString(),
                    401
            );
            writeErrorResponse(response, 401, errorResDto);
        };
    }

    private void writeErrorResponse(jakarta.servlet.http.HttpServletResponse response, int statusCode, ErrorResDTO errorDto) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            objectMapper.writeValue(outputStream, errorDto);
            outputStream.flush();
        }
    }
}
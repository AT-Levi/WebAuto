package uz.pdp.WebAuto.dtos.brand;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class BrandRequestDTO {
    private Long id;
    private String name;
    private String description;
    private String websiteUrl;
    private String country;
    private LocalDateTime createdDate;
    private MultipartFile icon;
}

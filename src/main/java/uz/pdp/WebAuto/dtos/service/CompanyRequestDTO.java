package uz.pdp.WebAuto.dtos.service;

import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {
    @NotBlank(message = "Center name is not blank")
    private String name;

    private String legalName;

    private AddressRequestDTO address;

    private MultipartFile image;

    private String phone;

    private String email;

    private String description;

    private Long userId;
}
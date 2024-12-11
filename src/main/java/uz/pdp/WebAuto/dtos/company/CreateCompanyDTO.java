package uz.pdp.WebAuto.dtos.company;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCompanyDTO {
    @NotBlank(message = "Center name is not blank")
    private String name;

    private String legalName;

    private AddressRequestDTO address;

    private String phone;

    private MultipartFile logo;

    private String email;

    private String description;

    private Long ownerId;
}
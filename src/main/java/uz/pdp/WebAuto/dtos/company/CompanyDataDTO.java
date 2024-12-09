package uz.pdp.WebAuto.dtos.company;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;

@Getter
@Setter
public class CompanyDataDTO {
    @NotNull
    private Long id;
    @NotNull
    private String name;    // Nomi
    private String legalName;
    @NotNull
    private AddressDTO address;     // Qonuniy nomi
    @NotNull
    private ImageResponseDTO logo;
    private String phone;
    private String email;
    private String description;
    @NotNull
    private Long ownerId;
}

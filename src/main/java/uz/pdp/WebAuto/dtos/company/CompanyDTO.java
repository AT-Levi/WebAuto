package uz.pdp.WebAuto.dtos.company;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.image.ImageDTO;

@Getter
@Setter
public class CompanyDTO {
    private String name;    // Nomi
    private String legalName;   // Qonuniy nomi
    private AddressDTO address;
    private ImageDTO logo;
    private String phone;
    private String email;
    private String description;
    private User owner;
}

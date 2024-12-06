package uz.pdp.WebAuto.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressRequestDTO address;
    private ImageRequestDTO profileImage;

}

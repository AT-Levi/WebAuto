package uz.pdp.WebAuto.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;

@Data
@Builder
@AllArgsConstructor
public class UserDataRequestDTO {

    @NotBlank
    @NotNull
    private String username;
    @NotNull
    @NotNull
    private String password;
    private String firstName;
    private String lastName;
    @NotNull
    private AddressRequestDTO address;

    @Email
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String phoneNumber;
}

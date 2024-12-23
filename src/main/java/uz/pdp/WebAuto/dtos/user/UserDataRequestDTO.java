package uz.pdp.WebAuto.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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

    @NotBlank(message = "City name is required")
    private String city;
    @NotBlank(message = "Street name is required")
    private String street;
    @NotBlank(message = "Apartment number is required")
    private String number;

    private String description;

    private Long longitude;
    private Long latitude;

    @Email
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    private String phoneNumber;
}

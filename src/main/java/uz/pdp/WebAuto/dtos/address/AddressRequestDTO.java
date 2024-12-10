
package uz.pdp.WebAuto.dtos.address;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequestDTO {
    @NotBlank(message = "City name is required")
    private String city;
    @NotBlank(message = "Street name is required")
    private String street;
    @NotBlank(message = "Apartment number is required")
    private String number;

    private String description;

    private Long longitude;
    private Long latitude;
}

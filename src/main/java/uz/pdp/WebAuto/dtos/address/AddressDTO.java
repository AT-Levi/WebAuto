package uz.pdp.WebAuto.dtos.address;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long id;

    private String city;

    private String street;

    private String number;

    private String description;

    private Long longitude;

    private Long latitude;
}
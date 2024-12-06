package uz.pdp.WebAuto.dtos.service;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;

@Getter
@Setter
public class ServiceDTO {

    private Long id;

    private String name;

    private String legalName;

    private AddressDTO address;

    private String logo;

    private String phone;

    private String email;

    private String description;

    private UserResponseDTO user;
}

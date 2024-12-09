package uz.pdp.WebAuto.dtos.company;

import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;

public record CompanyResponseDTO(
        Long id,
        UserResponseDTO owner,
        String name,
        AddressDTO address,
        ImageResponseDTO logo) {
}

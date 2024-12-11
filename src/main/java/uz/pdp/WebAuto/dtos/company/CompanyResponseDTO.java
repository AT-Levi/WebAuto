package uz.pdp.WebAuto.dtos.company;

import uz.pdp.WebAuto.dtos.address.AddressDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;

public record CompanyResponseDTO(
        Long id,
        String ownerName,
        String name,
        AddressDTO address,
        ImageResponseDTO logo) {
}

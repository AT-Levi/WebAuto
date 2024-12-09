package uz.pdp.WebAuto.dtos.brand;

import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;

import java.util.Date;

public record BrandDTO(
        Long id,
        String name,
        String description,
        ImageResponseDTO logoUrl,
        String websiteUrl,
        UserResponseDTO owner,
        String country,
        Date createdDate,
        ImageResponseDTO icon
) {

}

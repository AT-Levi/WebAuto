package uz.pdp.WebAuto.dtos.brand;

import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;

import java.util.Date;

public record BrandDTO(
        Long id,
        String name,
        String description,
        Long logoId,
        String logoUrl,
        String websiteUrl,
        String brandOwnerName,
        String country,
        Date createdDate,
        ImageResponseDTO icon
) {

}

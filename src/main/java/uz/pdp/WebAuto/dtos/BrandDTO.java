package uz.pdp.WebAuto.dtos;

import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.entity.User;

import java.util.Date;

public record BrandDTO(
        Long id,
        String name,
        String description,
        String logoUrl,
        String websiteUrl,
        User owner,
        String country,
        Date createdDate,
        Image icon
) {
}

package uz.pdp.WebAuto.dtos;

import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.entity.AuthUser;

import java.util.Date;

public record BrandDTO(
        String name,
        String description,
        String logoUrl,
        String websiteUrl,
        AuthUser owner,
        String country,
        Date createdDate,
        Image icon
) {
}

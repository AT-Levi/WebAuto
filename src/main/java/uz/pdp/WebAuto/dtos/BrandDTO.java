package uz.pdp.WebAuto.dtos;

import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.AbstractPersistable;
import uz.pdp.WebAuto.entity.Image;
import uz.pdp.WebAuto.entity.User;

import java.io.Serializable;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

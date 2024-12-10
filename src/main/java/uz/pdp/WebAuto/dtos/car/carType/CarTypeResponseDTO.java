package uz.pdp.WebAuto.dtos.car.carType;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;

@Getter
@Setter
public class CarTypeResponseDTO {

    private String name;

    private ImageResponseDTO carTypeIcon;
}

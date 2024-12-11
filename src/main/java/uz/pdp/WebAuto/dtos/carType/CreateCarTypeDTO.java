package uz.pdp.WebAuto.dtos.carType;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;

public record CreateCarTypeDTO(
        String typeName,

        @Schema(type = "string", format = "binary")
        MultipartFile icon) {

}

package uz.pdp.WebAuto.dtos.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequestDTO {

    private MultipartFile logo;
}
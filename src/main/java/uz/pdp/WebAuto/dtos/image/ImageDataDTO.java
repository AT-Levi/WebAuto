package uz.pdp.WebAuto.dtos.image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDataDTO {

    private String generatedName;

    private String originalName;

    private String url;

    private String prefix;

    private String mimeType;

    private String extension;
}

package uz.pdp.WebAuto.dtos.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ImageDataDTO {

    private Long id;

    private String fileName;

    private String originalName;

    private String url;

    private String mimeType;

    private String extension;
}

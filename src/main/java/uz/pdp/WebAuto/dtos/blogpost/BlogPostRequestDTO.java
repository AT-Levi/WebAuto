package uz.pdp.WebAuto.dtos.blogpost;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;

import java.util.List;

@Getter
@Setter
public class BlogPostRequestDTO {

    private String title;
    private String content;
    private List<ImageRequestDTO> picture;
}

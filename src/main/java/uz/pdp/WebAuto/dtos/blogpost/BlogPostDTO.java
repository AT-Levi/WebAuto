package uz.pdp.WebAuto.dtos.blogpost;

import lombok.Builder;
import lombok.Data;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class BlogPostDTO {

    private String title;
    private String content;
    private UserResponseDTO author;
    private LocalDate date;
    private List<ImageResponseDTO> pictures;
}

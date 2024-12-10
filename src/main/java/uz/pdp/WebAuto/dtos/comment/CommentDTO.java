package uz.pdp.WebAuto.dtos.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private Long ownerId;
    private String objectType; // Example: "CAR", "BRAND"
    private Long objectId;
}
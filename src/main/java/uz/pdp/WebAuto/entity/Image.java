package uz.pdp.WebAuto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image extends BaseEntity {
    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "generated_name", nullable = false)
    private String generatedName;

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "mime_type")
    private String mimeType;

    private String extension;
}

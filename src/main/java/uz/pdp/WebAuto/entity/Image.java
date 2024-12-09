package uz.pdp.WebAuto.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Image extends BaseEntity {

    @Column(name = "original_name", nullable = false)
    private String originalName;

    @Column(name = "generated_name", nullable = false)
    private String fileName;

    @Column(name = "image_url")
    private String url;

    @Column(name = "mime_type")
    private String mimeType;

    private String extension;

}

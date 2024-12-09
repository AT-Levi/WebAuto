package uz.pdp.WebAuto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Brand extends BaseEntity {

    private String name;

    private String description;

    @Column(name = "website_url")
    private String websiteUrl;

    private String country;

    @Column(name = "created_date")
    private String createdDate;

    @OneToOne(cascade = CascadeType.ALL)
    private Image icon;
}

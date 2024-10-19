package uz.pdp.WebAuto.entity;

import uz.pdp.WebAuto.entity.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "blog_post")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPost extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false, updatable = false)
    private User author;

    private LocalDate date;

    @OneToMany
    @JoinColumn(name = "picture_id", nullable = false)
    private List<Image> picture;

}

package uz.pdp.WebAuto.entity.blog;

import uz.pdp.WebAuto.entity.BaseEntity;
import uz.pdp.WebAuto.enums.ObjectType;
import uz.pdp.WebAuto.entity.user.AuthUser;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private AuthUser ownerId;

    @Column(name = "object_type", nullable = false)
    private ObjectType objectType;

    @Column(name = "object_id", nullable = false)
    private Long objectId;
}

package uz.pdp.WebAuto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.WebAuto.entity.user.AuthUser;
import uz.pdp.WebAuto.enums.UserRoleName;

import java.util.Set;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private UserRoleName name;

    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<AuthUser> users;
}

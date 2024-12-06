package uz.pdp.WebAuto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.Set;

@Entity
@Getter(AccessLevel.PUBLIC)
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private UserRole name;

    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

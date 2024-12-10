package uz.pdp.WebAuto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "car_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CarType extends BaseEntity {

    private String typeName;

    @OneToOne
    @JoinColumn(name = "logo_id", nullable = false)
    private Image icon;

    @OneToMany(mappedBy = "carType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Car> cars;
}




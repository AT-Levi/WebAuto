package uz.pdp.WebAuto.entity.car;

import uz.pdp.WebAuto.entity.BaseEntity;
import uz.pdp.WebAuto.entity.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarType extends BaseEntity {
    private String name;

    @OneToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image icon;
}

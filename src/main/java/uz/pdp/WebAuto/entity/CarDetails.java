package uz.pdp.WebAuto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CarDetails extends BaseEntity {

    private Long carId;
    // Dimensions
    private Long length; // uzunligi (mm da)
    private Long height; // balandligi (mm da)
    private Long wheelbase; // oldingi va orqa g'ildiraklar o'qlari orasidagi masofa (mm da)
    private Long heightWithRoofRails; // tomi bilan birgalikda balandligi (mm da)
    private Long widthWithMirrors;  // Yon oynalar bilan birgalikda kengligi (mm da)

    // Capacity
    private Long luggageCapacity;  // bagaj hajmi  (litr da)
    private Long width;     // kengligi             (litr da)
    private Long GrossVehicleWeight;    // Transportning umumiy og'irligi (kg da)
    private Long MaxLoadingWeight;  // Maksimal yuklash og'irligi (kg da)
    private Long MaxRoofLoad;   // maksimal tomga ortish mumkin bo'lgan yuk og'irligi (kg da)
    private Long NumberOfSeats;     // o'rindiqlar soni

    // Engine and Transmission
    private Long fuelTankCapacity;  // yoqilg'i baki hajmi (litrda)
    private Long maxTowingWeight;   // max tortish kuchi (kg da)
    private Long minTowingWeight;   // min tortish kuchi (kg da)
    private Long minKerbWeight;     // Og'irligi (kg da)
    private Long TurningCircle;     // min burilish aylanasi yoki radiusi (m da)

    // Car overview
    private Float engineSize;
    private Integer numberOfDoors;
    private Integer numberOfCylinder;


}

package uz.pdp.WebAuto.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDetailsDTO {
    private Long carId;
    private Long length;
    private Long height;
    private Long wheelbase;
    private Long heightWithRoofRails;
    private Long widthWithMirrors;
    private Long luggageCapacity;
    private Long width;
    private Long grossVehicleWeight;
    private Long maxLoadingWeight;
    private Long maxRoofLoad;
    private Long numberOfSeats;
    private Long fuelTankCapacity;
    private Long maxTowingWeight;
    private Long minTowingWeight;
    private Long minKerbWeight;
    private Long turningCircle;
    private Float engineSize;
    private Integer numberOfDoors;
    private Integer numberOfCylinder;
}
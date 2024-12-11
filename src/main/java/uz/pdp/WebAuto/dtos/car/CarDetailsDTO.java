package uz.pdp.WebAuto.dtos.car;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * CarDetailsDTO - Avtomobilning turli texnik xususiyatlarini o'z ichiga olgan DTO (Data Transfer Object) klassi.
 * Ushbu klass avtomobilning o'lchamlari, yuk tashish imkoniyatlari, dvigatel va uzatish tizimi haqida ma'lumotlarni saqlaydi.
 * <p>
 * Bu DTO avtomobil haqida muhim texnik tafsilotlarni saqlash uchun ishlatiladi, masalan:
 * 1. Avtomobil o'lchamlari (uzunlik, balandlik, kenglik va hokazo).
 * 2. Avtomobilning yuklash va tashish imkoniyatlari (bagaj hajmi, maksimal yuklash og'irligi va boshqalar).
 * 3. Dvigatel va uzatish tizimi haqida ma'lumotlar (yoqilg'i baki hajmi, maksimal tortish kuchi va boshqa texnik xususiyatlar).
 * 4. Avtomobilning umumiy ko'rinishi, shu jumladan dvigatel hajmi, eshiklar soni va silindrlar soni.
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(description = "Avtomobilning turli texnik xususiyatlarini o'z ichiga olgan DTO. Ushbu klass avtomobilning o'lchamlari, yuk tashish imkoniyatlari, dvigatel va uzatish tizimi haqida ma'lumotlarni saqlaydi.")
public class CarDetailsDTO {

    @ApiModelProperty(value = "Avtomobilning unikal identifikatori", required = true)
    private Long carId;

    // Dimensions (O'lchamlar)
    @ApiModelProperty(value = "Avtomobil uzunligi (mm da)")
    private Long length; // Uzunlik (mm da)

    @ApiModelProperty(value = "Avtomobil balandligi (mm da)")
    private Long height; // Balandlik (mm da)

    @ApiModelProperty(value = "Oldingi va orqa g'ildiraklar o'qlari orasidagi masofa (mm da)")
    private Long wheelbase; // Wheelbase (mm da)

    @ApiModelProperty(value = "Tomi bilan birgalikda balandligi (mm da)")
    private Long heightWithRoofRails; // Height with roof rails (mm da)

    @ApiModelProperty(value = "Yon oynalar bilan birgalikda kengligi (mm da)")
    private Long widthWithMirrors;  // Width with mirrors (mm da)

    // Capacity (Hajm va yuk tashish imkoniyatlari)
    @ApiModelProperty(value = "Bagaj hajmi (litr da)")
    private Long luggageCapacity;  // Luggage capacity (litrda)

    @ApiModelProperty(value = "Avtomobilning kengligi (mm da)")
    private Long width;     // Width (mm da)

    @ApiModelProperty(value = "Transportning umumiy og'irligi (kg da)")
    private Long GrossVehicleWeight;    // Gross vehicle weight (kg da)

    @ApiModelProperty(value = "Maksimal yuklash og'irligi (kg da)")
    private Long MaxLoadingWeight;  // Max loading weight (kg da)

    @ApiModelProperty(value = "Maksimal tomga ortish mumkin bo'lgan yuk og'irligi (kg da)")
    private Long MaxRoofLoad;   // Max roof load (kg da)

    @ApiModelProperty(value = "O'rindiqlar soni")
    private Long NumberOfSeats;     // Number of seats

    // Engine and Transmission (Dvigatel va uzatish tizimi)
    @ApiModelProperty(value = "Yoqilg'i baki hajmi (litrda)")
    private Long fuelTankCapacity;  // Fuel tank capacity (litrda)

    @ApiModelProperty(value = "Maksimal tortish kuchi (kg da)")
    private Long maxTowingWeight;   // Max towing weight (kg da)

    @ApiModelProperty(value = "Minimal tortish kuchi (kg da)")
    private Long minTowingWeight;   // Min towing weight (kg da)

    @ApiModelProperty(value = "Og'irligi (kg da)")
    private Long minKerbWeight;     // Min kerb weight (kg da)

    @ApiModelProperty(value = "Minimal burilish aylanasi yoki radiusi (m da)")
    private Long TurningCircle;     // Turning circle (m da)

    // Car overview (Avtomobil umumiy ko'rinishi)
    @ApiModelProperty(value = "Dvigatel hajmi (litrlarda)")
    private Float engineSize; // Engine size (liters)

    @ApiModelProperty(value = "Eshiklar soni")
    private Integer numberOfDoors; // Number of doors

    @ApiModelProperty(value = "Silindrlar soni")
    private Integer numberOfCylinder; // Number of cylinders
}


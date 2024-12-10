package uz.pdp.WebAuto.dtos.car;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.WebAuto.dtos.image.ImageRequestDTO;
import uz.pdp.WebAuto.enums.CarCondition;
import uz.pdp.WebAuto.enums.EngineType;
import uz.pdp.WebAuto.enums.FuelType;
import uz.pdp.WebAuto.enums.TransmissionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO for creating or updating car information")
public class CreateCarDTO {

    @Schema(description = "Model name of the car", example = "Toyota Corolla")
    private String model;

    @Schema(description = "Year the car was manufactured", example = "2022")
    private int year;

    @Schema(description = "Additional details about the car", example = "This car is in excellent condition")
    private String description;

    @Schema(description = "Color of the car", example = "Red")
    private String color;

    @Schema(description = "Warranty expiration date", example = "2025-12-31T23:59:59")
    private LocalDateTime warranty;

    @Schema(description = "Fuel type of the vehicle",
            allowableValues = {"GASOLINE", "DIESEL", "ELECTRIC", "HYBRID", "PETROL", "OTHER"})
    private FuelType fuelType;

    @Schema(description = "Transmission type for vehicles",
            allowableValues = {"MANUAL", "AUTOMATIC", "CVT", "SEMI_AUTOMATIC", "OTHER"})
    private TransmissionType transmission;

    @Schema(description = "Type of engine used in the car", example = "V6",
            allowableValues = {"INLINE", "V_TYPE", "BOXER", "ROTARY", "OTHER"})
    private EngineType engineType;

    @JoinColumn
    @Schema(description = "ID of the carType associated with the car", example = "1")
    private Long carTypeId;

    @Schema(description = "Mileage of the car in kilometers", example = "15000")
    private Long mileage;

    @Schema(description = "Condition of the car",
            allowableValues = {"NEW", "USED", "EXCELLENT", "GOOD", "FAIR", "POOR"})
    private CarCondition condition;

    @Schema(description = "ID of the brand associated with the car", example = "1")
    private Long brandId;

    @Schema(description = "Price of the car in USD", example = "25000.00")
    private BigDecimal price;

    @Schema(description = "List of car images for upload. Must be in multipart/form-data format.")
    private List<ImageRequestDTO> carImages;
}

package uz.pdp.WebAuto.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Fuel types available for vehicles")
public enum FuelType {

    @Schema(description = "Gasoline fuel type")
    GASOLINE,

    @Schema(description = "Diesel fuel type")
    DIESEL,

    @Schema(description = "Electric fuel type")
    ELECTRIC,

    @Schema(description = "Hybrid fuel type")
    HYBRID,

    @Schema(description = "Petrol fuel type")
    PETROL,

    @Schema(description = "Other fuel type")
    OTHER
}

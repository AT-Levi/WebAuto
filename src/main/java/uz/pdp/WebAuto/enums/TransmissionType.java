package uz.pdp.WebAuto.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Transmission type for vehicles")
public enum TransmissionType {

    @Schema(description = "Manual transmission, requires manual gear shifting")
    MANUAL,

    @Schema(description = "Fully automatic transmission, no manual gear shifting required")
    AUTOMATIC,

    @Schema(description = "Continuously Variable Transmission, provides seamless acceleration")
    CVT,

    @Schema(description = "Semi-automatic transmission, combines manual and automatic features")
    SEMI_AUTOMATIC,

    @Schema(description = "Other or unspecified transmission type")
    OTHER
}
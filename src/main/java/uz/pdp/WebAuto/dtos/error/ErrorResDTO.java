package uz.pdp.WebAuto.dtos.error;

import lombok.Getter;
import lombok.Setter;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class ErrorResDTO {
    private final String errorMessage;
    private final String errorPath;
    private final String reason;
    private final LocalDateTime timestamp;
    private final Integer errorCode;

    public ErrorResDTO(String errorMessage, String errorPath, String reason, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorPath = errorPath;
        this.errorCode = errorCode;
        this.reason = reason;
        this.timestamp = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
    }
}

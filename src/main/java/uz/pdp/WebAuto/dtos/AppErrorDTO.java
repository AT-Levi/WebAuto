package uz.pdp.WebAuto.dtos;

import lombok.Getter;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class AppErrorDTO {
    private final String errorMessage;
    private final String errorPath;
    private final String reason;
    private final LocalDateTime timestamp;
    private final Integer errorCode;

    public AppErrorDTO(String errorMessage, String errorPath, String reason, Integer errorCode) {
        this.errorMessage = errorMessage;
        this.errorPath = errorPath;
        this.errorCode = errorCode;
        this.reason = reason;
        this.timestamp = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
    }
}

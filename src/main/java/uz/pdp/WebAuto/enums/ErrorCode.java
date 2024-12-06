package uz.pdp.WebAuto.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(1000, "error.1000"),
    UNAUTHORIZED(1001, "error.1001"),
    ACCESS_DENIED(1002, "error.1002"),
    USER_NOT_FOUND(1003, "error.1003"),
    PASSWORD_IS_NOT_CORRECT(1004, "error.1004"),
    PHONE_ALREADY_USED(1005, "error.1005"),
    DATA_NOT_FOUND(1006, "error.1006"),
    NOT_FOUND_ERROR(1007, "error.1007");

    private final Integer code;
    private final String errorKey;
}
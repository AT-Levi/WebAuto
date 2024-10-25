package uz.pdp.WebAuto.dto;

public record AuthUserDto(
        String username,
        String password,
        String fullName,
        String email,
        String phoneNumber
) {
}

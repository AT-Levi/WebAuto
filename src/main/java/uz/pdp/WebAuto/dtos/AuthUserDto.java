package uz.pdp.WebAuto.dtos;

public record AuthUserDto(
        String username,
        String password,
        String fullName,
        String email,
        String phoneNumber
) {
}

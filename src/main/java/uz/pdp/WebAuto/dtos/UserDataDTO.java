package uz.pdp.WebAuto.dtos;

public record UserDataDTO(
        Long id,
        String username,
        String fullName,
        String password,
        String role,
        String status,
        String email,
        String phoneNumber
) {
}

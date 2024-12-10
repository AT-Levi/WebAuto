package uz.pdp.WebAuto.dtos.auth;

public record AuthRequestDTO(
        String username,
        String phoneNumber,
        String email,
        String password) {
}

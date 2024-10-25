package uz.pdp.WebAuto.dto;

public record AuthenticationDto(
        String usernameOrEmail,
        String password
) {
}

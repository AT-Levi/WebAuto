package uz.pdp.WebAuto.dtos;

public record AuthenticationDto(
        String usernameOrEmail,
        String password
) {
}

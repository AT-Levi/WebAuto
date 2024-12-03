package uz.pdp.WebAuto.dtos;

public record AuthenticationDTO(
        String usernameOrEmail,
        String password
) {
}

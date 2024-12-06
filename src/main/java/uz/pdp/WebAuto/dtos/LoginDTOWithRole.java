package uz.pdp.WebAuto.dtos;

import uz.pdp.WebAuto.dtos.auth.TokensDTO;

public record LoginDTOWithRole(TokensDTO tokensDTO, String role
// List<Car> cars
) {
}

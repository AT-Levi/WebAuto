package uz.pdp.WebAuto.dtos.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.role.RoleDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthResponseDTO {
    String accessToken;
    String refreshToken;
    List<RoleDTO> roles;
}

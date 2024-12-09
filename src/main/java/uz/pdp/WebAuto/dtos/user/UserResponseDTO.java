package uz.pdp.WebAuto.dtos.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.WebAuto.dtos.role.RoleDTO;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    @NotNull
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private Set<RoleDTO> roles;
    @NotNull
    private String username;
    @NotNull
    private String phoneNumber;
}

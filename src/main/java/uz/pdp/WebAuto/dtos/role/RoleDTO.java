package uz.pdp.WebAuto.dtos.role;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDTO {
    @NotNull
    private String roleName;
}

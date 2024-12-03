package uz.pdp.WebAuto.dtos;

import java.util.Set;

public record UserDTO(Long id, String username, String email, Set<RoleDTO> roles, Boolean deleted) {
}

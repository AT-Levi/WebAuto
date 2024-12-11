package uz.pdp.WebAuto.controller.admin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.dtos.role.RoleRequestDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.service.RoleService;
import uz.pdp.WebAuto.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/admin/role")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
@SecurityRequirement(name = "bearerAuth")
public class RoleManageController {

    private final RoleService roleService;
    private final UserService userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<Set<Role>> getUserRoles(@PathVariable String username) {
        Set<Role> roles = roleService.getRolesByUsername(username);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/add")
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        if (roleService.roleExists(roleRequestDTO.name())) {
            return ResponseEntity.badRequest().body(
                    RoleDTO.builder().roleName("Role already exists").build());
        }
        RoleDTO createdRole = roleService.save(roleRequestDTO);
        return ResponseEntity.ok(createdRole);
    }

    @PostMapping("/user/{username}/addRole")
    public ResponseEntity<String> assignRoleToUser(@PathVariable String username, @RequestBody RoleRequestDTO roleRequestDTO) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        if (!roleService.roleExists(roleRequestDTO.name())) {
            return ResponseEntity.badRequest().body("Role does not exist");
        }

        roleService.addRole(roleRequestDTO.name());
        return ResponseEntity.ok("Role successfully assigned to user");
    }

    @DeleteMapping("/user/{username}/removeRole")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable String username, @RequestBody RoleRequestDTO roleRequestDTO) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Set<Role> userRoles = roleService.getRolesByUsername(username);
        Role roleToRemove = roleService.findByName(UserRole.valueOf(roleRequestDTO.name().toUpperCase())).orElse(null);

        if (roleToRemove == null || !userRoles.contains(roleToRemove)) {
            return ResponseEntity.badRequest().body("Role not assigned to this user");
        }
        return ResponseEntity.ok("Role successfully removed from user");
    }
}

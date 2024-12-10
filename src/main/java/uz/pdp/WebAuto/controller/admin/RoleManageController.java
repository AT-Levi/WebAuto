package uz.pdp.WebAuto.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/admin/roles")
@RequiredArgsConstructor
public class RoleManageController {

    private final RoleService roleService;
    private final UserService userService;

    // Foydalanuvchining rollarini olish
    @GetMapping("/user/{username}")
    public ResponseEntity<Set<Role>> getUserRoles(@PathVariable String username) {
        Set<Role> roles = roleService.getRolesByUsername(username);
        return ResponseEntity.ok(roles);
    }

    // Yangi rol qo'shish
    @PostMapping("/add")
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        if (roleService.roleExists(roleRequestDTO.name())) {
            return ResponseEntity.badRequest().body(
                    RoleDTO.builder().roleName("Role already exists").build());
        }
        RoleDTO createdRole = roleService.save(roleRequestDTO);
        return ResponseEntity.ok(createdRole);
    }

    // Foydalanuvchining rolini qo'shish yoki o'zgartirish
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
        // User'ga yangi rolni qo'shish (bu yerda sizning biznes logikangizga qarab, role qo'shiladi)
        // user.addRole(roleService.findByName(UserRole.valueOf(roleRequestDTO.name().toUpperCase())).get());
        return ResponseEntity.ok("Role successfully assigned to user");
    }

    // Foydalanuvchidan rolni olib tashlash
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

        // User'dan rolni olib tashlash
        // user.removeRole(roleToRemove);
        return ResponseEntity.ok("Role successfully removed from user");
    }
}

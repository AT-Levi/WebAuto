package uz.pdp.WebAuto.controller.superAdmin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.service.UserService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RequestMapping("/superAdmin/admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
@RestController
public class AdminManageController {

    private final UserService userService;

    @GetMapping("/all-user-by-role")
    public ResponseEntity<ResponseDTO<List<User>>> showAllAdmin(String role) {
        List<User> usersByRole = userService.getUsersByRole(role);
        return ResponseDTO.ok(usersByRole);
    }

    @GetMapping("/block/{id}")
    public ResponseEntity<ResponseDTO<String>> updateUserStatus(@PathVariable("id") Long userId,
                                                                @RequestParam("userStatus") String status) {
        userService.updateUserStatus(userId, status);
        return ResponseDTO.ok("Admin is successfully blocked");
    }

    @PutMapping("/set-role")
    public ResponseEntity<ResponseDTO<String>> updateUserRole(@RequestParam Long userId,
                                                              @RequestParam String roleName) {
        userService.updateUserRole(userId, UserRole.valueOf(roleName));
        return ResponseDTO.ok("User's role changed");
    }
}


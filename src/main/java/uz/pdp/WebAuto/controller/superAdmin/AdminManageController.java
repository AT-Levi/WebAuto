package uz.pdp.WebAuto.controller.superAdmin;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.service.UserService;
import uz.pdp.WebAuto.util.ResponseDTO;

import java.util.List;

@RequestMapping("/super-admin")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RestController
public class AdminManageController {

    private final UserService userService;

    @GetMapping("/all-user-by-role")
    public ResponseEntity<ResponseDTO<List<User>>> showAllAdmin(String userRole) {
        List<User> admins = userService.getAllAdmin();
        return ResponseDTO.ok(admins);
    }

    @GetMapping("/all-admin")
    public ResponseEntity<ResponseDTO<List<User>>> showAllAdmin() {
        List<User> admins = userService.getAllAdmin();
        return ResponseDTO.ok(admins);
    }


}


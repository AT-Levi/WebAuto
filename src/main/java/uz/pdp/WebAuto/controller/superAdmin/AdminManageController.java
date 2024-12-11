package uz.pdp.WebAuto.controller.superAdmin;

import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(
            summary = "Foydalanuvchilarni rol bo‘yicha olish",
            description = "Foydalanuvchilarni ularning roli asosida olish uchun endpoint. Ushbu endpoint ma'lum bir rolga mos keluvchi foydalanuvchilarni filtrlaydi."
    )
    @GetMapping("/all-user-by-role")
    public ResponseEntity<ResponseDTO<List<User>>> showAllUsersByRole(String userRole) {
        List<User> admins = userService.getAllAdmin(); // userRole uchun filtrni amalga oshirish
        return ResponseDTO.ok(admins);
    }

    @Operation(
            summary = "Barcha admin foydalanuvchilarni olish",
            description = "Tizimdagi barcha admin foydalanuvchilar ro‘yxatini olish uchun endpoint. Ushbu endpoint faqat admin hisoblarni qaytaradi."
    )
    @GetMapping("/all-admin")
    public ResponseEntity<ResponseDTO<List<User>>> showAllAdmins() {
        List<User> admins = userService.getAllAdmin();
        return ResponseDTO.ok(admins);
    }
}

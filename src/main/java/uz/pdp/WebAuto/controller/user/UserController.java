package uz.pdp.WebAuto.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserDataDTO;
import uz.pdp.WebAuto.dtos.user.UserDataRequestDTO;
import uz.pdp.WebAuto.service.UserService;
import uz.pdp.WebAuto.util.ResponseDTO;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPER_ADMIN', 'DEALER')")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final ObjectMapper objectMapper;


    @GetMapping("/about-me")
    @Operation(summary = "foydalanuvchi o'zi haqidagi ma'lumotlarni oladi")
    public ResponseEntity<ResponseDTO<UserDataDTO>> me() {
        return ResponseDTO.ok(userService.me());
    }

    @Operation(
            summary = "Foydalanuvchi profil rasmiga yuklash",
            description = "Bu endpoint foydalanuvchi profil rasmiga yuklash imkoniyatini beradi."
    )
    @PostMapping(value = "/profileImage", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseDTO<ImageResponseDTO>> profileImage(
            @RequestPart("profileImage") MultipartFile profileImage) {
        return ResponseDTO.ok(userService.saveProfileImage(profileImage));
    }

    @PostMapping(value = "/update-data", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Foydalanuvchi o'zi haqida qo'shimcha ma'lumot qo'shadi")
    public ResponseEntity<ResponseDTO<UserDataDTO>> updateUserData(
            @RequestPart("user")
            @Parameter(description = "User details") String userData,
            @RequestPart("profileImage")
            @Parameter(description = "User profile image") MultipartFile profileImage) {
        UserDataRequestDTO userDataDTO;
        try {
            userDataDTO = objectMapper.readValue(userData, UserDataRequestDTO.class);
        } catch (JsonProcessingException e) {
            return ResponseDTO.error("Invalid JSON format", HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
        }

        UserDataDTO updatedUser = userService.updateUserData(userDataDTO, profileImage);
        return ResponseDTO.ok(updatedUser);
    }
}

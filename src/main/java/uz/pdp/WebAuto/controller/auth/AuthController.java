package uz.pdp.WebAuto.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.LoginDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserDataDTO;
import uz.pdp.WebAuto.dtos.user.UserDataRequestDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.service.UserService;
import uz.pdp.WebAuto.util.ResponseDTO;

@RestController
@RequestMapping("/auth")
public record AuthController (UserService service) {

    @PostMapping("/register")
    @Operation(summary = "Register")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> register(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseDTO.ok(service.register(authRequestDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<ResponseDTO<TokensDTO>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseDTO.ok(service.login(loginDTO));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token")
    public ResponseEntity<ResponseDTO<RefreshTokenResponseDTO>> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseDTO.ok(service.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/about-me")
    @Operation(summary = "foydalanuvchi o'zi haqidagi ma'lumotlarni oladi")
    public ResponseEntity<ResponseDTO<UserDataDTO>> me() {
        return ResponseDTO.ok(service.me());
    }

    @PostMapping("/update-data")
    @Operation(summary = "Foydalanuvchi o'zi haqida qo'shimcha ma'lumot qo'shadi")
    public ResponseEntity<ResponseDTO<UserDataDTO>> updateUserData(UserDataRequestDTO userData){
        return ResponseDTO.ok(service.updateUserData(userData));

    }

}

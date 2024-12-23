package uz.pdp.WebAuto.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.LoginDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
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

}

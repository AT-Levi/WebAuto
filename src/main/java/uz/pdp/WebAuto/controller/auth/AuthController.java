package uz.pdp.WebAuto.controller.auth;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyRequestDTO;
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
    public ResponseEntity<ResponseDTO<TokensDTO>> login(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseDTO.ok(service.login(authRequestDTO));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh token")
    public ResponseEntity<ResponseDTO<RefreshTokenResponseDTO>> refresh(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return ResponseDTO.ok(service.refreshToken(refreshTokenRequestDTO));
    }

    @GetMapping("/me")
    @Operation(summary = "me")
    public ResponseEntity<ResponseDTO<UserResponseDTO>> me() {
        return ResponseDTO.ok(service.me());
    }

    @PostMapping("/company")
    @Operation(summary = "Create Company. Register qilgan odam company yaratishi shart")
    public ResponseEntity<ResponseDTO<CompanyDataDTO>> createCompany(@Valid @RequestBody CompanyRequestDTO dto) {
        return ResponseDTO.ok(service.createCompany(dto));
    }

    @PostMapping(value = "/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "logo")
    public ResponseEntity<ResponseDTO<CompanyDataDTO>> refreshCompanyLogo(
            @RequestParam("companyId") Long companyId, @RequestPart("logo") MultipartFile logo) {
        return ResponseDTO.ok(service.refreshCompanyLogo(companyId, logo));
    }

}

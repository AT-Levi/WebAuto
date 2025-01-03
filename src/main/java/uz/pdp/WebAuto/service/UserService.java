package uz.pdp.WebAuto.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.LoginDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserDataDTO;
import uz.pdp.WebAuto.dtos.user.UserDataRequestDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserResponseDTO register(AuthRequestDTO authRequestDTO);

    TokensDTO login(LoginDTO loginDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    UserDataDTO me();

    Optional<User> findById(Long id);

    User findByUsername(String username);

    void updateUserRole(Long userId, UserRole roleName);

    ImageResponseDTO saveProfileImage(MultipartFile profileImage);

    UserDataDTO updateUserData(UserDataRequestDTO userData, MultipartFile profileImage);

    void updateUserStatus(Long userId, String status);

    List<User> getUsersByRole(String role);
}

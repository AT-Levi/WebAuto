package uz.pdp.WebAuto.service;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.company.CompanyDTO;
import uz.pdp.WebAuto.dtos.service.CompanyRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserRequestDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.User;

import java.util.Optional;

public interface UserService {

    UserResponseDTO register(AuthRequestDTO authRequestDTO);

    TokensDTO login(AuthRequestDTO authRequestDTO);

    RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    UserResponseDTO me();

    CompanyDTO createCompany(CompanyRequestDTO companyRequestDTO);

    Optional<User> findById(Long id);

    CompanyDTO saveLogo(MultipartFile logo);

    User findByUsername(String username);
}

package uz.pdp.WebAuto.service.impl;

import uz.pdp.WebAuto.config.CurrentUser;
import uz.pdp.WebAuto.config.JWTService;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.AuthResponseDTO;
import uz.pdp.WebAuto.dtos.company.CompanyDataDTO;
import uz.pdp.WebAuto.dtos.company.CompanyRequestDTO;
import uz.pdp.WebAuto.dtos.role.RoleDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.RoleMapper;
import uz.pdp.WebAuto.mapper.UserMapper;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.repository.UserRepository;
import uz.pdp.WebAuto.service.CompanyService;
import uz.pdp.WebAuto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CurrentUser currentUser;
    private final CompanyService companyServiceImp;
    private final RoleMapper roleMapper;

    @Override
    public AuthResponseDTO login(AuthRequestDTO dto) {

        var user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!user.getPhoneNumber().equals(dto.phoneNumber())) {
            throw new RuntimeException("Bunday raqamli USER mavjud emas");
        }

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Password doesn't match");
        }

        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.phoneNumber(),
                dto.password()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        Set<String> roleNames = user.getRoles()
                .stream()
                .map(role -> role.getName().name()) // Map orqali nomlarni olish
                .collect(Collectors.toSet()); // To'plamga yig'ish
        List<RoleDTO> rolesDto = roleMapper.toDto((List<Role>) user.getRoles());

        return AuthResponseDTO
                .builder()
                .accessToken(jwtService.accessToken(dto.username(), roleNames))
                .refreshToken(jwtService.refreshToken(dto.username(), roleNames))
                .roles(rolesDto)
                .build();
    }

    @Override
    public UserResponseDTO register(AuthRequestDTO authRequestDTO) {
        var user = User
                .builder()
                .phoneNumber(authRequestDTO.phoneNumber())
                .password(passwordEncoder.encode(authRequestDTO.password()))
                .roles(Set.of(roleRepository.findById(1L).orElseThrow(() -> new NotFoundException("Role"))))
                .build();
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        var refreshToken = refreshTokenRequestDTO.getRefreshToken();
        var username = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username not found"));

        return RefreshTokenResponseDTO.builder()
                .accessToken(jwtService.accessToken(username, getRoleNames(user.getRoles())))
                .build();
    }

    public Set<String> getRoleNames(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getName().name()) // Map orqali nomlarni olish
                .collect(Collectors.toSet()); // To'plamga yig'ish
    }

    @Override
    public UserResponseDTO me() {
        return userMapper.toDto(
                userRepository
                        .findByUsername(currentUser.getCurrentUsername())
                        .orElseThrow(() -> new NotFoundException("User")));
    }

    @Override
    public CompanyDataDTO createCompany(CompanyRequestDTO companyRequestDTO) {
        return companyServiceImp.save(companyRequestDTO);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public CompanyDataDTO refreshCompanyLogo(Long companyId, MultipartFile logo) {
        return companyServiceImp.refreshCompanyLogo(companyId, logo);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User "));
    }
}
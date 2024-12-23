package uz.pdp.WebAuto.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.WebAuto.config.JWTService;
import uz.pdp.WebAuto.config.service.CurrentUser;
import uz.pdp.WebAuto.dtos.address.AddressRequestDTO;
import uz.pdp.WebAuto.dtos.auth.AuthRequestDTO;
import uz.pdp.WebAuto.dtos.auth.LoginDTO;
import uz.pdp.WebAuto.dtos.auth.TokensDTO;
import uz.pdp.WebAuto.dtos.image.ImageResponseDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenRequestDTO;
import uz.pdp.WebAuto.dtos.token.RefreshTokenResponseDTO;
import uz.pdp.WebAuto.dtos.user.UserDataDTO;
import uz.pdp.WebAuto.dtos.user.UserDataRequestDTO;
import uz.pdp.WebAuto.dtos.user.UserResponseDTO;
import uz.pdp.WebAuto.entity.*;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.enums.UserStatus;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.mapper.UserDataMapper;
import uz.pdp.WebAuto.mapper.UserMapper;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.repository.UserRepository;
import uz.pdp.WebAuto.service.ImageService;
import uz.pdp.WebAuto.service.UserService;

import java.util.*;
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
    private final ImageService imageService;
    private final UserDataMapper userDataMapper;

    @Override
    public TokensDTO login(LoginDTO dto) {

        User user = userRepository.findByUsername(dto.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Password doesn't match");
        }
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(role -> role.getName().name()) // Extract role names
                .collect(Collectors.toSet());

        Set<GrantedAuthority> authorities = roleNames.stream()
                .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName)) // Prefix role with "ROLE_"
                .collect(Collectors.toSet());

        var authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.username(),
                dto.password(),
                authorities
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return TokensDTO
                .builder()
                .accessToken(jwtService.accessToken(dto.username(), roleNames))
                .refreshToken(jwtService.refreshToken(dto.username(), roleNames))
                .build();
    }

    @Override
    public UserResponseDTO register(AuthRequestDTO dto) {
        Set<Role> defaultRole = Set.of(roleRepository.findByName(UserRole.USER).orElseThrow(RuntimeException::new));

        var user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .password(passwordEncoder.encode(dto.password()))
                .roles(defaultRole)
                .build();

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Transactional
    public void updateUserRole(Long userId, UserRole roleName) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        var role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRoles(Set.of(role));
        userRepository.save(user);
    }

    @Override
    public ImageResponseDTO saveProfileImage(MultipartFile profileImage) {
        User user = findByUsername(currentUser.getCurrentUser().getUsername());
        Image image = imageService.save(profileImage);
        user.setProfileImage(image);
        return ImageResponseDTO.builder()
                .id(image.getId())
                .url(image.getUrl())
                .build();
    }

    @Override
    public UserDataDTO updateUserData(UserDataRequestDTO userData,
                                      MultipartFile profileImage) {
        User user = userRepository.findByUsername(currentUser.getCurrentUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        AddressRequestDTO address = userData.getAddress();
        Address build = Address.builder()
                .city(address.getCity())
                .number(address.getNumber())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .street(address.getStreet())
                .description(address.getDescription())
                .build();
        user.setAddress(build);
        user.setEmail(userData.getEmail());
        user.setPassword(passwordEncoder.encode(userData.getPassword()));
        Image save = imageService.save(profileImage);
        user.setProfileImage(save);
        user.setUsername(user.getUsername());
        user.setPhoneNumber(userData.getPhoneNumber());
        user.setLastName(userData.getLastName());
        user.setFirstName(userData.getFirstName());
        User savedUser = userRepository.save(user);
        return userDataMapper.toDto(savedUser);
    }

    @Override
    public void updateUserStatus(Long userId, String status) {
        UserStatus userStatus = UserStatus.valueOf(status);
        userRepository.updateUserStatus(userId, userStatus);
    }

    @Override
    public List<User> getUsersByRole(String roleStr) {
        List<User> usersByRole = new ArrayList<>();

        if (roleStr.equals(UserRole.USER.name())) {
            usersByRole = userRepository.findUsersByRoleId(2);
        } else if (roleStr.equals(UserRole.ADMIN.name())) {
            usersByRole = userRepository.findUsersByRoleId(1);
        } else if (roleStr.equals(UserRole.SUPER_ADMIN.name())) {
            usersByRole = userRepository.findUsersByRoleId(4);
        } else if (roleStr.equals(UserRole.DEALER.name())) {
            usersByRole = userRepository.findUsersByRoleId(3);
        }
        return usersByRole;
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
    public UserDataDTO me() {
        User user = findByUsername(currentUser.getCurrentUsername());

        List<String> rolesStr = getRolesStr(user.getRoles());

        return UserDataDTO.builder()
                .id(user.getId())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .status(user.getStatus().name())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(rolesStr)
                .build();
    }

    private List<String> getRolesStr(Set<Role> roles) {
        return roles.stream().map((role) -> role.getName().name()).toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User "));
    }
}
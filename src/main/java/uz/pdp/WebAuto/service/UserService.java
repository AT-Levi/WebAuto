package uz.pdp.WebAuto.service;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.AuthUserDTO;
import uz.pdp.WebAuto.dtos.UserDataDTO;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.AuthUser;
import uz.pdp.WebAuto.enums.UserRoleName;
import uz.pdp.WebAuto.handler.exception.UserDeletedException;
import uz.pdp.WebAuto.handler.exception.UserNotFoundException;
import uz.pdp.WebAuto.repository.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import static uz.pdp.WebAuto.mapper.UserMapper.USER_MAPPER;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(AuthUserDTO user) {
        AuthUser authUser = USER_MAPPER.toEntity(user); // DTO dan entity ga o'tish
        HashSet<Role> roles = new HashSet<>();

        roles.add(roleService.findByName(UserRoleName.USER).orElseThrow(() ->
                new RuntimeException("Role not found"))); // Role topilmasa exception
        authUser.setRoles(roles);

        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));

        userRepository.save(authUser);
    }

    @Transactional
    public void save(AuthUser user) {
        userRepository.save(user);
    }

    public void isDeleted(AuthUserDTO user) {
        if (userRepository.idDeleted(USER_MAPPER.toEntity(user).getUsername())) {
            throw new UserDeletedException("User is deleted");
        }
    }

    public Long getIdWithUsername(String username) {
        return userRepository.getIdWithUsername(username);
    }

    public Optional<AuthUser> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public UserDataDTO getUserById(Long id) {
        return USER_MAPPER.toDto(findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found")
        ));
    }

    public Optional<AuthUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

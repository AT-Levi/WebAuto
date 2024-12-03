package uz.pdp.WebAuto.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.AuthUser;
import uz.pdp.WebAuto.enums.UserRoleName;
import uz.pdp.WebAuto.handler.exception.UserNotFoundException;
import uz.pdp.WebAuto.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;

    public RoleService(RoleRepository roleRepository, @Lazy UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public List<String> getRoles(String username) {
        AuthUser authUser = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found."));

        List<String> roles = new ArrayList<>();

        if (authUser.getRoles() != null) {
            authUser.getRoles().forEach(role -> roles.add(role.getName().toString()));
        }

        return roles;
    }

    public Optional<Role> findByName(UserRoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}

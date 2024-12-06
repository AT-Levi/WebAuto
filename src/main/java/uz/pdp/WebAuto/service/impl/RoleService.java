package uz.pdp.WebAuto.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.handler.exception.UserNotFoundException;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.service.UserService;

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
        User user = userService.findByUsername(username);

        List<String> roles = new ArrayList<>();

        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> roles.add(role.getName().toString()));
        }

        return roles;
    }

    public Optional<Role> findByName(UserRole roleName) {
        return roleRepository.findByName(roleName);
    }
}

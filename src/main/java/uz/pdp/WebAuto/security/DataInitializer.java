package uz.pdp.WebAuto.security;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.user.AuthUser;
import uz.pdp.WebAuto.enums.UserRoleName;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.repository.UserRepository;

import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        Role adminRole = createRoleIfNotFound(UserRoleName.ADMIN, UserRoleName.ADMIN.getDescription());
        Role userRole = createRoleIfNotFound(UserRoleName.USER, UserRoleName.USER.getDescription());
        Role dealerRole = createRoleIfNotFound(UserRoleName.DEALER, UserRoleName.DEALER.getDescription());
        Role superAdminRole = createRoleIfNotFound(UserRoleName.SUPER_ADMIN, UserRoleName.SUPER_ADMIN.getDescription());

        if (userRepository.findByUsername("admin").isEmpty()) {
            HashSet<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            roles.add(superAdminRole);
            roles.add(dealerRole);

            AuthUser adminUser = AuthUser.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("123"))
                    .email("admin@gmail.com")
                    .roles(roles)
                    .build();

            userRepository.save(adminUser);
        }
    }

    public Role createRoleIfNotFound(UserRoleName roleName, String description) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    newRole.setDescription(description);
                    return roleRepository.save(newRole);
                });
    }
}

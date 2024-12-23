package uz.pdp.WebAuto.security;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.WebAuto.entity.Address;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.enums.UserStatus;
import uz.pdp.WebAuto.repository.AddressRepository;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Transactional
    @Override
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        Role adminRole = createRoleIfNotFound(UserRole.ADMIN, UserRole.ADMIN.getDescription());
        Role userRole = createRoleIfNotFound(UserRole.USER, UserRole.USER.getDescription());
        Role dealerRole = createRoleIfNotFound(UserRole.DEALER, UserRole.DEALER.getDescription());
        Role superAdminRole = createRoleIfNotFound(UserRole.SUPER_ADMIN, UserRole.SUPER_ADMIN.getDescription());


        if (userRepository.findByUsername("admin").isEmpty()) {
            HashSet<Role> roles = new HashSet<>();
            roles.add(adminRole);
            roles.add(userRole);
            roles.add(superAdminRole);
            roles.add(dealerRole);


            Address addressAdmin = Address.builder()
                    .city("Tashkent")
                    .street("Chilanzar")
                    .number("234A")
                    .longitude(41L)
                    .latitude(69L)
                    .build();
            Address addressSuperAdmin = Address.builder()
                    .city("Tashkent")
                    .street("Olmazor")
                    .number("23")
                    .longitude(32L)
                    .latitude(29L)
                    .build();

            addressRepository.save(addressAdmin);
            addressRepository.save(addressSuperAdmin);

            var superAdmin = User.builder()
                    .username("superadmin")
                    .phoneNumber("1234567890")
                    .email("superadmin@gmail.com")
                    .address(addressSuperAdmin)
                    .firstName("admin")
                    .lastName("super")
                    .status(UserStatus.ACTIVE)
                    .password(passwordEncoder.encode("1"))
                    .roles(Set.of(superAdminRole))
                    .build();

            User adminUser = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("2"))
                    .email("admin@gmail.com")
                    .roles(roles)
                    .address(addressAdmin)
                    .build();

            userRepository.save(adminUser);
            userRepository.save(superAdmin);
        }
    }

    public Role createRoleIfNotFound(UserRole roleName, String description) {
        return roleRepository.findByName(roleName)
                .orElseGet(() -> {
                    Role newRole = new Role();
                    newRole.setName(roleName);
                    newRole.setDescription(description);
                    return roleRepository.save(newRole);
                });
    }
}

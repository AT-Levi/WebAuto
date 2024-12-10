package uz.pdp.WebAuto.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.WebAuto.entity.Address;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.enums.UserStatus;
import uz.pdp.WebAuto.exception.NotFoundException;
import uz.pdp.WebAuto.repository.AddressRepository;
import uz.pdp.WebAuto.repository.RoleRepository;
import uz.pdp.WebAuto.repository.UserRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Log4j2
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            roleRepository.save(Role.builder().name(UserRole.SUPER_ADMIN).description(UserRole.SUPER_ADMIN.getDescription()).build());
            roleRepository.save(Role.builder().name(UserRole.ADMIN).description(UserRole.ADMIN.getDescription()).build());
            roleRepository.save(Role.builder().name(UserRole.DEALER).description(UserRole.DEALER.getDescription()).build());
            roleRepository.save(Role.builder().name(UserRole.EMPLOYEE).description(UserRole.EMPLOYEE.getDescription()).build());
            roleRepository.save(Role.builder().name(UserRole.USER).description(UserRole.USER.getDescription()).build());
        }

        if (userRepository.count() == 0) {
            var superAdminRole = roleRepository.findByName(UserRole.SUPER_ADMIN)
                    .orElseThrow(() -> new NotFoundException("SuperAdmin role "));

            Address superAdminAddress = Address.builder()
                    .city("Tashkent")
                    .street("Chilanzar")
                    .number("234A")
                    .build();

            log.info("Saving address...");
            addressRepository.save(superAdminAddress);

            var superAdmin = User.builder()
                    .username("superadmin")
                    .phoneNumber("1234567890")
                    .email("superadmin@gmail.com")
                    .address(superAdminAddress)
                    .firstName("admin")
                    .lastName("super")
                    .status(UserStatus.ACTIVE)
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(superAdminRole))
                    .build();

            log.info("Saving super admin...");
            userRepository.save(superAdmin);
        }
    }
}



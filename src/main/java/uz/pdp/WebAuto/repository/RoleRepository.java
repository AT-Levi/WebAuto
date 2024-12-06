package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}

package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.enums.UserRoleName;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRoleName name);
}

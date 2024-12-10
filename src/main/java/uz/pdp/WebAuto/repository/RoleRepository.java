package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.Role;
import uz.pdp.WebAuto.enums.UserRole;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRole name);

    @Query(nativeQuery = true,
            value = "SELECT ur.role_id FROM user_roles ur WHERE ur.user_id = :userId")
    Set<Role> findByUserId(@Param("userId") Long userId);

    boolean existsByName(UserRole userRole);
}

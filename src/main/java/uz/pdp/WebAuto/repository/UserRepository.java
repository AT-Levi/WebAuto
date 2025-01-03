package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.User;
import uz.pdp.WebAuto.enums.UserRole;
import uz.pdp.WebAuto.enums.UserStatus;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("UPDATE User u SET u.status = :status WHERE u.id = :userId")
    void updateUserStatus(@Param("userId") Long userId, @Param("status") UserStatus status);

    @Query(nativeQuery = true,
            value = "SELECT u.* FROM users u INNER JOIN user_roles ur ON u.id = ur.user_id WHERE ur.role_id = :roleId")
    List<User> findUsersByRoleId(@Param("roleId") int roleId);

}

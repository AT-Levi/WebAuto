package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.WebAuto.entity.user.AuthUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByUsername(String username);

    @Query("SELECT a.deleted FROM AuthUser a WHERE a.username = :username")
    boolean idDeleted(String username);

    @Query("SELECT au.id FROM AuthUser au WHERE au.username = :username")
    Long getIdWithUsername(@Param("username") String username);
}

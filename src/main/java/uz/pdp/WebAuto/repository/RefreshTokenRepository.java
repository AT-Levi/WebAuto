package uz.pdp.WebAuto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    void deleteWithUserId(Long userId);
}

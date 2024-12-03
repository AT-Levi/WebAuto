package uz.pdp.WebAuto.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.WebAuto.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {


    void deleteByUserId(Long userId);

}

package uz.pdp.WebAuto.services;

import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dto.AuthenticationDto;
import uz.pdp.WebAuto.dto.Tokens;
import uz.pdp.WebAuto.entity.user.User;
import uz.pdp.WebAuto.exception.UserNotFoundException;
import uz.pdp.WebAuto.repository.RefreshTokenRepository;
import uz.pdp.WebAuto.repository.UserRepository;
import uz.pdp.WebAuto.util.JwtTokenUtil;

@Service
public class RefreshTokenService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Tokens getTokens(AuthenticationDto user) {
        Long userId = getUserIdWithUsernameOrEmail(user.usernameOrEmail());
        User authUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Auth user not found."));

        String refreshToken = jwtTokenUtil.generateToken(user.usernameOrEmail());
        String accessToken = jwtTokenUtil.generateToken(user.usernameOrEmail());
        deleteWithUserId(userId);
        save(refreshToken, userId);
        return new Tokens(accessToken, refreshToken);
    }

    private void save(String refreshToken, Long userId) {

    }

    private void deleteWithUserId(Long userId) {
        refreshTokenRepository.deleteWithUserId(userId);
    }

    private Long getUserIdWithUsernameOrEmail(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail);

        return user.getId();
    }


}

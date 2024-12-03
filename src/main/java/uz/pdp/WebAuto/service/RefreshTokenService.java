package uz.pdp.WebAuto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.WebAuto.dtos.AuthUserDTO;
import uz.pdp.WebAuto.dtos.TokensDTO;
import uz.pdp.WebAuto.entity.RefreshToken;
import uz.pdp.WebAuto.entity.AuthUser;
import uz.pdp.WebAuto.repository.RefreshTokenRepository;
import uz.pdp.WebAuto.security.CustomUserDetailsService;
import uz.pdp.WebAuto.security.JwtTokenUtil;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final Integer REFRESH_TOKEN_EXPIRES_IN = 1000 * 60 * 60 * 24 * 7;
    private static final Integer ACCESS_TOKEN_EXPIRES_IN = 1000 * 60 * 5;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public void save(String refreshToken, Long userId) {
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .token(refreshToken)
                        .userId(userId)
                        .build()
        );
    }

    public TokensDTO getTokens(AuthUserDTO user) {
        Long userId = getUserIdWithUsername(user.username());
        AuthUser authUser = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Auth user not found."));

        Set<String> roles = authUser.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());

        String refreshToken = jwtTokenUtil.generateToken(authUser.getUsername(), roles, REFRESH_TOKEN_EXPIRES_IN);
        String accessToken = jwtTokenUtil.generateToken(authUser.getUsername(), roles, ACCESS_TOKEN_EXPIRES_IN);
        deleteWithUserId(userId);
        save(refreshToken, userId);
        return new TokensDTO(accessToken, refreshToken);
    }

    private void deleteWithUserId(Long userId) {
        refreshTokenRepository.deleteWithUserId(userId);
    }

    private Long getUserIdWithUsername(String username) {
        return userService.getIdWithUsername(username);
    }

    public TokensDTO refresh(TokensDTO tokens) {
        String username = jwtTokenUtil.extractUsername(tokens.refreshToken());
        jwtTokenUtil.validateToken(tokens.refreshToken(),
                customUserDetailsService.loadUserByUsername(username));
        findByToken(tokens.refreshToken());
        Long userId = getUserIdWithUsername(username);
        AuthUser authUser = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("Auth user not found."));
        Set<String> roles = authUser.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet());
        String newAccessToken = jwtTokenUtil.generateToken(username, roles, ACCESS_TOKEN_EXPIRES_IN);

        return new TokensDTO(newAccessToken, tokens.refreshToken());
    }

    public void findByToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token not found."));
    }
}

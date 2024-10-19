package uz.pdp.WebAuto.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.WebAuto.dtos.AuthUserDto;
import uz.pdp.WebAuto.dtos.AuthenticationDto;
import uz.pdp.WebAuto.dtos.LoginDto;
import uz.pdp.WebAuto.dtos.Tokens;
import uz.pdp.WebAuto.entity.user.User;
import uz.pdp.WebAuto.services.RefreshTokenService;
import uz.pdp.WebAuto.services.UserService;

import static uz.pdp.WebAuto.mapper.UserMapper.USER_MAPPER;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<LoginDto> getTokens(@RequestBody AuthenticationDto user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.usernameOrEmail(), user.password());
        authenticationManager.authenticate(authenticationToken);
        userService.isDeleted(user);
        Tokens tokens = refreshTokenService.getTokens(user);
        return ResponseEntity.ok(new LoginDto(tokens));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(AuthUserDto authUserDto) {
        User user = USER_MAPPER.toEntity(authUserDto);
        userService.saveUser(user);
        return ResponseEntity.ok(user);
    }
}
